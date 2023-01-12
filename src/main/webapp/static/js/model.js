async function getResponse(url){
    const response = await fetch(url);
    return  await response.json();

}
async function PostResponse(url, data) {
    const setupObj = {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(data),
    }
    const response = await fetch(url, setupObj);
    return response.json();
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
    editCartContent: async function(data){
        await PostResponse("/api/editProdOnCart",data)
    }
};