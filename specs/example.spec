# Signup Specification

This is an executable specification file. This file follows markdown syntax.
Every heading in this file denotes a scenario. Every bulleted point denotes a step.

To execute this specification, run
	gauge run specs

## Successful signup

tags: successful signup

* Open the "http://127.0.0.1:7000" page.
* Check if there is a greeting object with "/html/body/main/div[1]/h1" path
* Open "/html/body/header/nav/ul/li[2]/a" page and check the object with "sign-up__email" id
* Enter the data into fields
     | name | surname | email                 | password   | confirmation |
     |------|---------|-----------------------|------------|--------------|
     | asdf | asdf    | asdf.asdf@example.com | Asdf@1234  | Asdf@1234    |
* Click Signup and check the greeting message