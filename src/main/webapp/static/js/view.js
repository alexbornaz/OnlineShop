import {builder} from "./builder.js";

export let display = {
    showContent: function (selector, content){
        document.querySelector(selector).innerHTML=content;
    },
    showProducts: function (products){
        let productContent = products.map(product => {
            return builder.buildCard(product);
        }).join('');
        this.showContent('#products', productContent);
    }
}