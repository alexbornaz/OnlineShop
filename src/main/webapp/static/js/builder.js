export let builder = {
    buildCard: function (product){
            return `<div class="col col-sm-12 col-md-6 col-lg-4">
                <div class="card">
                    <img class="" src="/static/img/product_${product.id}.jpg" alt="http://placehold.it/400x250/000/fff"/>
                    <div class="card-header">
                        <h4 class="card-title">${product.name}</h4>
                        <p class="card-text">${product.description}</p>
                    </div>
                    <div class="card-body">
                        <div class="card-text">
                            <p class="lead">${product.defaultPrice} ${product.defaultCurrency}</p>
                        </div>
                        <div class="card-text">
                        <button data-btn-id="${product.id}" class="btn btn-success cart-btn">Add to cart</button>
                        </div>
                    </div>
                </div>
            </div>`;
    }

}