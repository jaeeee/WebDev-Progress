import React from 'react';
import './App.css';

import { Button, Form, FormGroup, Label, Input }
  from 'reactstrap';

import { FacebookLoginButton } from 'react-social-login-buttons';


class App extends React.Component {
  render() {
    return (
      <Form className="login-form">
      <h1><span className="font-weight-bold">mywebsite</span>.com</h1>
      <h2 className="text-center">Welcome</h2>
      <FormGroup>
        <Label>Email</Label>
        <Input type="email" placeholder="Email"/>
      </FormGroup>
        <FormGroup>
          <Label>Password</Label>
          <Input type="password" placeholder="password" />
        </FormGroup>
        <Button className="btn-lg btn-dark btn-block">
          Log in</Button>
          <div className="text-center pt-3">
            Or continue with social account
            </div>
            <FacebookLoginButton className="mt-3 mb-3" />
            <div className="text-center">
              <a href="/sign-up">Sign up</a>
              <span className="p-2">|</span>
              <a href="/sign-up">Forgot password</a>
            </div>
      </Form>
    );
  }
}

export default App;
