import {dataHandler} from "../DataHandler/dataHandler.js";
import {domManager} from "../view/domManager.js";

export let CartController = {
    container: new Map(),
    productList: null,
    cartItem: null,
    numberOfItems: 0,
    numberOfItemsTag: null,
    totalPriceTag: null,
    initCart: function () {
        initAddToCartButtons();
        CartController.productList = document.getElementById('productList');
        CartController.cartItem = document.getElementById("cartItem");
        CartController.numberOfItemsTag = document.getElementById("numberOfItems");
        CartController.numberOfItemsTag.textContent = CartController.numberOfItems + " items";
        CartController.totalPriceTag = document.getElementById("totalPrice");
        CartController.totalPriceTag.textContent = "0$";
    },
    updateAddToCartButtons: function () {
        initAddToCartButtons();
    },
    addToCart: function (productId) {
        if (CartController.container.has(productId)) {
            CartController.container.set(productId, CartController.container.get(productId) + 1);
            CartController.numberOfItems++;
            refreshCart();
        } else {
            CartController.container.set(productId, 1);
            CartController.numberOfItems++;
            refreshCart();
        }
    },
    clearCart: function () {
        document.getElementById('productList').innerHTML = " ";
    }
}
    function initAddToCartButtons() {
    const buttons = document.querySelectorAll(".cart-btn");
    buttons.forEach((button) => button.addEventListener('click', addProductToCart));
}
function addProductToCart(clickEvent) {
    const productId = clickEvent.currentTarget.dataset.productId;
    CartController.addToCart(productId);
}
function refreshCart() {
    dataHandler.getCart(Object.fromEntries(CartController.container)).then((response) => {
        domManager.clearElement(CartController.productList);
        response.items.forEach((item) => createProductCardInCart(item));
        CartController.totalPriceTag.textContent = response.total + "$";
    }).catch((error) => {
        alert(error);
    });
    CartController.numberOfItemsTag.textContent = CartController.numberOfItems + " items";
}
function createProductCardInCart(item) {
    const cardDiv = CartController.cartItem.content.cloneNode(true);

    const image = cardDiv.querySelector(".product-image");
    if (item.supplier !== "") {
        image.setAttribute("src", `/static/img/product_${item.id}.jpg`);
    }

    const itemName = cardDiv.querySelector(".product-name");
    itemName.textContent = item.name;

    const itemSupplier = cardDiv.querySelector(".product-supplier");
    itemSupplier.textContent = item.supplier;

    const itemQuantity = cardDiv.querySelector(".product-quantity");
    itemQuantity.textContent = item.quantity;

    const price = cardDiv.querySelector(".product-price");
    price.textContent = item.SumPrice + " $";

    const deleteButton = cardDiv.querySelector(".close");
    deleteButton.setAttribute("data-product-id", item.id);
    deleteButton.addEventListener('click', removeProduct);

    const increaseButton = cardDiv.querySelector(".increase-quantity");
    increaseButton.setAttribute("data-product-id", item.id);
    increaseButton.addEventListener('click', increaseProductQuantity);

    const decreaseButton = cardDiv.querySelector(".decrease-quantity");
    decreaseButton.setAttribute("data-product-id", item.id);

    decreaseButton.addEventListener('click', decreaseProductQuantity);

    CartController.productList.appendChild(cardDiv);

}
function removeProduct(clickEvent) {
    const productId = clickEvent.currentTarget.dataset.productId;
    CartController.numberOfItems = CartController.numberOfItems - CartController.container.get(productId);
    CartController.container.delete(productId);
    refreshCart();
}
function increaseProductQuantity(clickEvent) {
    const productId = clickEvent.currentTarget.dataset.productId;
    if (CartController.container.has(productId)) {
        CartController.container.set(productId, CartController.container.get(productId) + 1);
        CartController.numberOfItems++;
        refreshCart();
    }
}

function decreaseProductQuantity(clickEvent) {
    const productId = clickEvent.currentTarget.dataset.productId;
    if (CartController.container.has(productId)) {
        let newValue = CartController.container.get(productId) - 1;
        if (newValue === 0) {
            CartController.numberOfItems--;
            CartController.container.delete(productId);
            refreshCart();
            return;
        }
        CartController.container.set(productId, CartController.container.get(productId) - 1);
        CartController.numberOfItems--;
        refreshCart();
    }
}