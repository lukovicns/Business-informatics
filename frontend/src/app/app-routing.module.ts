import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { PageNotFoundComponent } from './components/page-not-found/page-not-found.component';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import { AccountsComponent } from './components/accounts/accounts.component';
import { AnalyticalStatementsComponent } from './components/analytical-statements/analytical-statements.component';

const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'accounts', component: AccountsComponent },
  { path: 'analytical-statements', component: AnalyticalStatementsComponent },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: '**', component: PageNotFoundComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
