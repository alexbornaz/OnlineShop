export let dataHandler = {
    getCart: async function (cart) {
        console.log(cart)
        return await apiPost('/api/cart-info', cart);
    },
    getProducts: async function () {
        return await apiGet('/api/product');
    }
}

async function apiGet(url) {
    let response = await fetch(url, {
        method: "GET",
    });
    if (!response.ok) {
        throw Error(`${response.status} ${response.statusText}`);
    }
    return response.json();
}

async function apiPost(url, payload) {
    let response = await fetch(url, {
        method: "POST",
        headers: {"content-type":"application/json"},
        body:JSON.stringify(payload)

    });
    if(!response.ok) {
        throw Error(`${response.status} ${response.statusText}`);
    }
    return response.json();
}