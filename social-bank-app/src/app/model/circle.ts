import { Post } from './post';
import { User } from './user';

export class Circle {

   
    constructor(public circleId?: string,
                public circleName?: string,
                public circleDescription?: string,
                public users?: Array<User>,
                public posts?: Array<Post>,
                public createdBy?: string)
                {

                }
}
