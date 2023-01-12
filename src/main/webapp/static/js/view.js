import {builder} from "./builder.js";
import {calculateTotalPrice,engageEditCart} from "./controller.js"

export let display = {
    showContent: function (selector, content){
        document.querySelector(selector).innerHTML=content;
    },
    showProducts: function (products){
        let productContent = products.map(product => {
            return builder.buildCard(product);
        }).join('');
        this.showContent('#products', productContent);
    },
    showCart: function (cartInfo){
        let cartContent = cartInfo.map(product =>{
            return builder.buildCardForCart(product);
        }).join('');
        this.showContent('#cart-body',cartContent);
        calculateTotalPrice(cartInfo);
        engageEditCart();
    }
}