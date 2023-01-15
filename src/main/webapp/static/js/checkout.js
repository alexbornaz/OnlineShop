import {responses} from "./model.js";

function init() {
check();
}
function check(){
    let orderbtn = document.querySelector("#order-btn")
    orderbtn.addEventListener("click", async ()=>{
        // let customerData = [...document.querySelectorAll("#customer-data input")]
        // if (checkValid(customerData)){
        //     await responses.confirmOrder({"firstName": customerData[0].value,
        //         "email" : customerData[1].value, "address": customerData[2].value, "city":customerData[3].value,
        //     "state":customerData[4].value, "zip": customerData[5].value, "cardName": customerData[6].value,
        //     "cardNumber": customerData[7].value, "expDate":customerData[8].value,"cvv":customerData[9].value})
        // }
        await responses.confirmOrder({"firstName" : "alex"})
    })

}

function checkPattern(element){
    let pattern =new RegExp(element.pattern)
    element.addEventListener("change",()=>{
        console.log(pattern.test(element.value))
    })
    return pattern.test(element.value)
}
function checkValid(customerData){
    for (let data of customerData){
        if (checkPattern(data)){
            return true
        }
    }
    return false
}


init()