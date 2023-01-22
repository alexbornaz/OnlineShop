export let builder = {
    buildCard: function (product) {
        return `<div class="col col-sm-12 col-md-6 col-lg-4">
                <div class="card">
                    <img class="" src="/static/img/product_${product.id}.jpg" alt="http://placehold.it/400x250/000/fff" width="200"/>
                    <div class="card-header">
                        <h4 class="card-title">${product.name}</h4>
                        <p class="card-text">${product.description}</p>
                    </div>
                    <div class="card-body">
                        <div class="card-text">
                            <p class="lead">${product.price}</p>
                        </div>
                        <div class="card-text">
                        <button data-product-id="${product.id}" class="btn btn-success cart-btn">Add to cart</button>
                        </div>
                    </div>
                </div>
            </div>`;
    },
    buildCardForCart: function (product) {
        return `<tr>
            <td class="w-25">
               <img class="" src="/static/img/product_${product.id}.jpg" alt="http://placehold.it/400x250/000/fff"/>
            </td>
            <td id="product-name">${product.name}</td>
            <td>${product.defaultPrice} ${product.defaultCurrency}</td>
            <td class="qty"><p id=${"amountId" + product.id} type="text" class="amount form-control" >${product.quantity}</p></td>
            <td id=${"product-total" + product.id} data-default-price="${product.defaultPrice}" data-default-currency="${product.defaultCurrency}">
                ${product.defaultPrice * product.quantity} ${product.defaultCurrency}
            </td>
            <td data-product-id="${product.id}">
                <h2 data-value="1" class="edit-cart-qty">+</h2>
                <h2 data-value="-1" class="edit-cart-qty">-</h2>
            </td>
        </tr>`;
    }
}