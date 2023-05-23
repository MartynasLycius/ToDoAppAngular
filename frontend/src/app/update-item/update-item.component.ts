import { Component } from '@angular/core';
import { Item } from '../item';
import { ItemService } from '../item.service';
import { ActivatedRoute, Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-update-item',
  templateUrl: './update-item.component.html',
  styleUrls: ['./update-item.component.css']
})
export class UpdateItemComponent {
  id: number;
  item: Item = new Item();
  itemForm!: FormGroup;
  submitted = false;

  constructor(private itemService: ItemService,
    private route: ActivatedRoute,
    private router: Router,
    private formBuilder: FormBuilder){
  }

  ngOnInit(): void{
    this.id = this.route.snapshot.params['id'];
    this.itemService.getItem(this.id).subscribe(data => {
      this.item = data
      this.itemForm.setValue({
        name: this.item.name,
        description: this.item.description,
        toDoDate: this.item.toDoDate
      });
    });

    this.itemForm = this.formBuilder.group({
      name: ['', Validators.required],
      description: ['', Validators.required],
      toDoDate: ['', [Validators.required, Validators.pattern(/^\d{4}\-(0[1-9]|1[012])\-(0[1-9]|[12][0-9]|3[01])$/)]]
    });    
  }


  get f() { return this.itemForm.controls; }
  
  onSubmit(){
    this.submitted = true;
    if (this.itemForm.invalid) {
      return;
    }

    this.itemService.updateItem(this.id, this.itemForm.value).subscribe({
      complete: () => this.router.navigate(['/items'])
    });      
  }

}
