import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {FormControl, FormGroup} from "@angular/forms";
import Swal from "sweetalert2";
import {AuthGuardService} from "../../service/auth-guard.service";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit{
  loginForm: FormGroup;
  constructor(private router: Router, private service: AuthGuardService) {
    this.loginForm = new FormGroup({
      username: new FormControl(''),
      password: new FormControl('')
    });
  }

  ngOnInit() {
  }

  onSubmit() {
    var usuario = this.loginForm.value;

    if (usuario.username == 'admin' && usuario.password == '12345') {
      //redirigir las dasboard
      Swal.fire({
        position: 'center',
        icon: 'success',
        title: 'Bienvenido Ganaderia nuevo amanecer',
        showConfirmButton: false,
        timer: 1500
      });
      this.service.authenticateToken(usuario.username, usuario.password).subscribe(
        res => {
          console.log(res);
          const token = res.jwtToken;
          console.log("token "+ token);
         localStorage.setItem('token', res.jwtToken);
        },
        err => {
          console.log(err);
        }
      );
      localStorage.setItem('username', usuario.username);
      this.router.navigate(['/navegacion/inicio']);
    } else {
      Swal.fire({
        icon: 'error',
        title: 'Oops...',
        text: 'Usuario o contraseña incorrecta',
      });
    }

  }
}
