async function getResponse(url){
    const response = await fetch(url);
    return  await response.json();

}


export let responses = {
    getFilteredProducts: async function (categoryId, supplierId) {
        return await getResponse(`/api/filtered?categoryId=${categoryId}&supplierId=${supplierId}`);
    },
    sendProdToCart:async function(prodId) {
        return await  getResponse(`/api/addToCart?prodId=${prodId}`)
    },
    getCartInfo: async function(){
        return await getResponse("/api/cartInfo")
    },
};