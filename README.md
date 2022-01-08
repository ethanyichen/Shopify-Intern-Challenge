# Shopify-Intern-Challenge

### Author: Yichen Zhang
### Email: Easonzyc_@outlook.com

### Prerequisites
### 1. MongoDB installed https://docs.mongodb.com/manual/tutorial/install-mongodb-on-windows/
### 2. Intellij installed
### 3. Java installed
### 4. Have one of the platforms to call REST API endpoints. 
- https://www.postman.com/ 
- https://insomnia.rest/ 
- chrome extension https://chrome.google.com/webstore/detail/advanced-rest-client/hgmloofddffdnphfgcellkdfbfbjeloo

## Steps to set up
1. Open the project in intellij
2. After project loads, run InventoryWebserviceApplication (If some classes show up as not found, try maven clean + install in the maven tool tab of intellij or try refresh maven in the maven tool tab)
3. You are now able to use the web application by calling the list of endpoints below!

## List Of Item Endpoints
1. Create a new item \
   POST http://localhost:8080/item | Request Header {name: "{item name of your choice}"} \
   return the UUID of the item just created
2. Get item by itemID \
   GET http://localhost:8080/item/{itemID}
3. remove an item \
   DELETE http://localhost:8080/item/remove | Request Header {itemID: "{itemID of the item you want to remove}"}
4. show a list of all the items \
   GET http://localhost:8080/items
5. update the quantity of an item \
  PUT http://localhost:8080/item/quantity/{itemID} | Request Header {quantity: "{new quantity of the item}"}
## List of Group Associated Endpoints
1. create a new itemGroup \
POST http://localhost:8080/group | Request Header {name: "{group name of your choice}"} 
2. get a group by groupID \
GET http://localhost:8080/group/{groupID}
3. show all the members of the group \
GET http://localhost:8080/group/members/{groupID}
4. assign an item to a group | Request Header {itemID: "{itemID of the item to be assigned}", grouopID: "{groupID to assigned to}"} \ 
POST http://localhost:8080/group/assign
5. remove an item from its group | Request Header {itemID: "{itemID of the item to be removed from its group}"} \
POST http://localhost:8080/group/remove
