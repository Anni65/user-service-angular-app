import { Component, OnInit } from '@angular/core';
import {Users} from "../../model/users";
import {UserService} from "../../services/user.service";

@Component({
  selector: 'app-add-user',
  templateUrl: './add-user.component.html',
  styleUrls: ['./add-user.component.css']
})
export class AddUserComponent implements OnInit {
  user: Users = {
    id: undefined,
    firstName: '',
    lastName: '',
    email: ''
  };
  submitted = false;

  constructor(private userService: UserService) { }

  ngOnInit(): void {
  }

  saveUser():void{
    const data = {
      firstName: this.user.firstName,
      lastName: this.user.lastName,
      email: this.user.email
    };
    this.userService.create(data)
      .subscribe(
        (response: any) => {
          console.log(response);
          this.submitted = true;
        },
        (error: any) => {
          console.log(error);
        }
      );
  }

  newUser(): void {
    this.submitted = false;
    this.user = {
      id: undefined,
      firstName: '',
      lastName: '',
      email: ''
    };
  }
}
