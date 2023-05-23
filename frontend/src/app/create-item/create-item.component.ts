import { Component, OnInit } from '@angular/core';
import { Item } from '../item';
import { ItemService } from '../item.service';
import { Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-create-item',
  templateUrl: './create-item.component.html',
  styleUrls: ['./create-item.component.css']
})
export class CreateItemComponent implements OnInit {
  item: Item = new Item();
  itemForm!: FormGroup;
  submitted = false;

  constructor(private itemService: ItemService,
    private router: Router,
    private formBuilder: FormBuilder){ }

  ngOnInit() {
    this.itemForm = this.formBuilder.group({
      name: ['', Validators.required],
      description: ['', Validators.required],
        // validates date format yyyy-mm-dd
      toDoDate: ['', [Validators.required, Validators.pattern(/^\d{4}\-(0[1-9]|1[012])\-(0[1-9]|[12][0-9]|3[01])$/)]]
    });
  }

  get f() { return this.itemForm.controls; }


  onSubmit(){
    this.submitted = true;
    if (this.itemForm.invalid) {
      return;
    }
    this.itemService.saveItem(this.itemForm.value).subscribe({
      complete: () => this.router.navigate(['/items'])
    });   
  }


}
