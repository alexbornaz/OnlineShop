import {responses} from "./model.js";
import {display} from "./view.js";
import {builder} from "./builder.js";


function init(){
    addEventListeneR("#categories",filterProd);
    addEventListeneR("#suppliers",filterProd);
    addToCartBtnsAction();
    checkCart();
}

export function engageEditCart(){
    console.log("merge")
    document.querySelectorAll(".edit-cart-qty")
        .forEach(e =>  e.addEventListener("click",async ()=>{
            console.log("merge")
        await editCart(e);
    }))
}
async function editCart(e){
    const editValue = Number(e.dataset.value);
    console.log(editValue)
    const productId = e.parentElement.dataset.productId;
    const quantity = Number(document.querySelector(`#amountId${productId}`).innerText)
    await responses.editCartContent({"id": productId, "quantity": editValue})
    if (quantity + editValue < 1 ){
        await loadCart();
    }else {
        const defaultPrice = Number(document.querySelector(`#product-total${productId}`).dataset.defaultPrice)
        const defaultCurrency = document.querySelector(`#product-total${productId}`).dataset.defaultCurrency
        const total = Number(document.querySelector("#total-price").dataset.total)
        editCartProductAmount(editValue, quantity, productId, defaultCurrency, defaultPrice)
        editTotalCartPrice(editValue, total, defaultPrice)
    }
}
function editCartProductAmount(value, quantity, id, defaultCurrency, defaultPrice){
    document.querySelector(`#amountId${id}`).textContent = String(Math.round(quantity + value))
    document.querySelector(`#product-total${id}`).textContent = `${defaultPrice * (quantity + value)} ${defaultCurrency}`
}
function editTotalCartPrice(value, total, defaultPrice){
    if (value === 1){
        document.querySelector("#total-price").textContent = (total + defaultPrice) + "$"
        document.querySelector("#total-price").dataset.total = String(total + defaultPrice)
    } else if (value === -1){
        document.querySelector("#total-price").textContent = total - defaultPrice + "$"
        document.querySelector("#total-price").dataset.total = String(total - defaultPrice)
    }
}
function addEventListeneR(selector, func) {
    document.querySelector(selector).addEventListener('change', func);
}

async function loadCart(){
    let cartInfo = await responses.getCartInfo();
    display.showCart(cartInfo);
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
function checkCart(){
    let viewCart = document.getElementById("cart");
    viewCart.addEventListener("click", async() =>{
        await loadCart();
    })
}
export function calculateTotalPrice(cartInfo){
        const totalPrice = cartInfo.map(product => {
            return Number(product.defaultPrice) * Number(product.quantity)
        }).reduce((a, b) => a + b, 0)
        document.querySelector("#total-price").innerText = String(totalPrice) + "$"
        document.querySelector("#total-price").dataset.total = String(totalPrice)
}
init();