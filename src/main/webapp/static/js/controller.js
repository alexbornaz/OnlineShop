import {responses} from "./model.js";
import {display} from "./view.js";


function init(){
    addEventListener("#categories",filterProd);
    addEventListener("#suppliers",filterProd);
}
function addEventListener(selector, func) {
    document.querySelector(selector).addEventListener('change', func);
}

async function filterProd() {
        let categoryId = document.getElementById('categories').value;
        console.log(categoryId)
        let supplierId = document.getElementById('suppliers').value;
        console.log(supplierId)
        let products = await responses.getFilteredProducts(categoryId, supplierId)
        console.log(products)
        display.showProducts(products)

}
init();