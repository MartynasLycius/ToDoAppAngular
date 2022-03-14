export class Todo {

    id: string;
    name: string;
    description: string;
    date: Date | null;
    // createdAt: Date;
    // updatedAt: Date;

    constructor(options: any = {}) {

        this.id = options.id || '';
        this.name = options.name || '';
        this.description = options.description || '';

        this.date = options.date ? new Date(options.date) : null;
        // this.createdAt = options.createdAt ? new Date(options.createdAt) : null;
        // this.updatedAt = options.updatedAt ? new Date(options.createdAt) : null;

    }
}
