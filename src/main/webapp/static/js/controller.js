import {responses} from "./model.js";
import {display} from "./view.js";


function init(){
    addEventListener("#categories",filterProd);
    addEventListener("#suppliers",filterProd);
    addToCartBtnsAction();
}
function addEventListener(selector, func) {
    document.querySelector(selector).addEventListener('change', func);
}

async function filterProd() {
        let categoryId = document.getElementById('categories').value;
        let supplierId = document.getElementById('suppliers').value;
        console.log(supplierId)
        let products = await responses.getFilteredProducts(categoryId, supplierId)
        display.showProducts(products)

}

async function addToCart(e){
    const prodId = e.target.dataset.btnId;
    await responses.sendProdToCart(prodId);
}

function addToCartBtnsAction(){
    let addToCartBtns = document.querySelectorAll(".cart-btn")
    addToCartBtns.forEach((cartBtn) =>
        cartBtn.addEventListener("click",async (e) =>{
            await addToCart(e);
        }))
}
init();