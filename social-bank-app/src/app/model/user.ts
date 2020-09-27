import { Circle } from './circle';
import { Post } from './post';
import { Product } from './product';

export class User {
    
    constructor(public userId: string,
                public userName: string,
                public dob: string,
                public userRole: string,
                public circles: Array<Circle>,
                public products: Array<Product>,
                public circleRequests: Array<Circle>,
                public posts: Array<Post>,
                public gender: string)
    {

    }
}
