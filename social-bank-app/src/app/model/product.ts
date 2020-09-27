import { User } from './user';

export class Product {
    
    constructor(public productId: string,
                public productName: string,
                public description: string,
                public productImage: string,
                public users: Array<User>)
    {

    }
}
