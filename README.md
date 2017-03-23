# SPRING LOVE
academic spring project (IMT. A. - FIL2019)

# Guidelines
### GIT
#### main branches
There are two main branches to the repository:
* **master** : *production ready state*
* **dev** : *lastest development state*

#### feature branches
##### 
> $ git checkout **-b** *myfeature* dev
##### Incorporating a finished feature to dev
switch to branch dev

> $ git checkout dev

merge myfeature to dev

> $ git merge **--no-ff** myfeature

delete myfeature branch

> $ git branch -d myfeature

push to repository

> $ git push myremote dev

### RESTfull APIs
* [POST] - **create**
  * [code 201] - Created;
  * [code 404] - Not Found (error);
  * [code 409] - Conflict (ressource already exists).
* [GET] - **read**
  * [code 200] - OK, data in body;
  * [code 404] - Not Found (no ressource).
* [PUT] - **update / replace**
  * [code 200] - OK;
  * [code 204] - No Content;
  * [code 404] - Not Found.
* [PATCH] - **update / modify**
  * [code 200] - OK;
  * [code 204] - No Content;
  * [code 404] - Not Found.
* [DELETE] - **delete**
  * [code 200] - OK;
  * [code 204] - No Content;
  * [code 404] - Not Found.

*source: www.restapitutorial.com*

# Contributors
* Lemée Bertine,
* Grison Rémi,
* Guerin Emeric,
* Mainchain Mael,
* Laot Rémi. 
