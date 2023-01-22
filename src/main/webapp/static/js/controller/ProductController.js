import {dataHandler} from "../DataHandler/dataHandler.js";
import {display} from "../view.js";
import {CartController} from "./CartController.js";

export let ProductController = {
    initProducts: function() {
        dataHandler.getProducts().then((response) => {
            ProductController.products = response;
        }).catch((error) => {
            alert(error);
        });
    },
    products: null,
    filterProductsByCategory: function(categoryId) {
        let filteredProducts = ProductController.products.filter(product => product.categoryId.toString() === categoryId);
        console.log(filteredProducts)
        display.showProducts(filteredProducts);
        CartController.updateAddToCartButtons();
    },
    filterProductBySupplier: function (supplierId) {
        let filteredProducts = ProductController.products.filter(product => product.supplierId.toString() === supplierId);
        display.showProducts(filteredProducts);
        CartController.updateAddToCartButtons();
    },
    getAllProducts: function() {
        display.showProducts(ProductController.products);
        CartController.updateAddToCartButtons();
    }
}