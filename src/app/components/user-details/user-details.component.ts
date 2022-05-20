import { Component, OnInit } from '@angular/core';
import {Users} from "../../model/users";
import {UserService} from "../../services/user.service";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-user-details',
  templateUrl: './user-details.component.html',
  styleUrls: ['./user-details.component.css']
})
export class UserDetailsComponent implements OnInit {
  currentUser: Users = {
    id: undefined,
    firstName: '',
    lastName: '',
    email: ''
  };
  message = '';

  constructor(
    private userService: UserService,
    private route: ActivatedRoute,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.message = '';
    this.getUser(this.route.snapshot.params['id']);
  }

  getUser(id: number): void {
    this.userService.getUser(id)
      .subscribe( data =>{
          this.currentUser = data;
          console.log(data);
        },
        error => {
          console.log(error);
        });
  }

  updateUser(): void {
    this.message = '';
    this.userService.update(this.currentUser.id, this.currentUser)
      .subscribe(response => {
          console.log(response);
          this.message = response.message ? response.message : 'User [' + this.currentUser.id + '] was updated successfully !';
        },
        error => {
          console.log(error);
        });
  }

  deleteUser(): void {
    this.userService.delete(this.currentUser.id)
      .subscribe(response => {
          console.log(response);
          this.router.navigate(['/users']);
        },
        error => {
          console.log(error);
        });
  }
}
