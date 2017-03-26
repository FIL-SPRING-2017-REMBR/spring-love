# SPRING LOVE - SERVER
## RESTfull API

#### Skill

* **/skill/add/?params** : *adds a new skill*
  
  **name**=value : the name of the new skill (**mandatory**)

        POST : server-adress.com/skill/add/?name=Robert
  
* **/skill/link/skill/?params** : *links two skills*
  
  **skillId1**=value : the name of the parent skill (**mandatory**)</br>
  **skillId2**=value : the name of the child skill (**mandatory**)

        PUT : server-adress.com/skill/link/skill/?skillId1=01&skillId2=02

* **/skill/link/ress/?params** : *links a skill to an external ressource*
  
  **skillId**=value : the name of the skill (**mandatory**)</br>
  **ress**=value : the ressource as a string (**mandatory**)

        PUT : server-adress.com/skill/link/ress/?skillId=01&ress="http://www.google.com"

* **/skill/get/?params** : *retrieves skills*

  **all** : get all skills (*default*)</br>
  **contains**=value : get skills containing value in their title (*optional*)</br>
  **name**=value : get skills with a specific name (*optional*)</br>
  **id**=value : (*optional*)
  
        GET : server-adress.com/skill/get/?contains="Rob"

TODO:

#### Worker

* **/worker/add/?params** : *adds a new worker*

        example:
* **/worker/link/exp/?params** : *links a worker to an experience*

        example:
* **/worker/link/ress/?params** : *links a worker to an external ressource*

        example:
* **/worker/def/level/?params** : *defines the level of a worker in a specific skill*

        example:
* **/worker/def/trend/?params** : *defines the trend of the level of a worker in a specific skill*

        example:
* **/worker/get/?params** : *retrieves workers*

        example:
#### Experience

* **/exp/add/?params** : *adds a new experience*

        example:
* **/exp/get/?params** : *retrieves experiences*

        example:
