document.addEventListener('DOMContentLoaded',() =>{
let exchangeRates = {};
const loginForm = document.getElementById("loginForm");
const registerForm = document.getElementById("registerForm");
const convertButton = document.getElementById("convertButton");
const historyButton = document.getElementById("historyButton");

if(convertButton){
convertButton.addEventListener('click', () => {
convertCurrency();
});
}
if(historyButton){
historyButton.addEventListener('click', () => {
window.location.href = "./view_history";
});
}
if(loginForm){
loginForm.addEventListener('submit', (e) => {
e.preventDefault();
formSubmit('./login', loginForm);
});
}
if(registerForm){
registerForm.addEventListener('submit', (e) => {
  e.preventDefault();
  formSubmit('./register', registerForm);
  });
}
 });

const formSubmit = (url,form) => {
const FORM_DATA = new FormData(form);
const formData =  new URLSearchParams(FORM_DATA);

fetch(url, {
    method: 'POST',
     headers:{
        "Content-Type":"application/x-www-form-urlencoded"
        },
    body: formData

})
.then((r) => {
    if (!r.ok) {
      throw new Error(`Error! status: ${r.status}`);
    }
    return r.json();
})
.then((data) => {
        window.location.href = "/" + data.redirect;

})
.catch((error)=>{
    console.error("Error", error);
    });
  };


const createDBTables = () => {
  console.log("Called: createDBTables");
  // STEP 1:
  const URL_CREATE_TABLES = "./createDBTables";

  fetch(URL_CREATE_TABLES, {
    method: "POST",
    headers:{
    "Content-Type":"application/json"
    },
    body:JSON.stringify( {
            secret:"111111",
        })
  })
     .then((r)=>{
        if(!r.ok){
        throw new Error(`Error! status: ${r.status}`);
        }
        return r.json();
        })
    .then((data)=>{
        console.log("Success", data)
        })
        .catch((error)=>{
        console.error("Error", error)
        })
};

function exportCurrency(){

  const URL_POPULATE_TABLES = "./exportCurrencyToCSV";

  fetch(URL_POPULATE_TABLES,{
      method: "POST",
    })
    .then((r)=>{
    if(!r.ok){
    throw new Error(`Error! status: ${r.status}`);
    }
    return r.json();
    })
    .then((data)=>{
    console.log("success")
    })
    .catch((error)=>{
    console.error("Error", error)
    })

};



function exportUser(){

  const URL_POPULATE_TABLES = "./exportUsersToCSV";

  fetch(URL_POPULATE_TABLES,{
      method: "POST",
    })
    .then((r)=>{
    if(!r.ok){
    throw new Error(`Error! status: ${r.status}`);
    }
    return r.json();
    })
    .then((data)=>{
    console.log("success")
    })
    .catch((error)=>{
    console.error("Error", error)
    })

};
function exportConversionHistory(){

  const URL_POPULATE_TABLES = "./exportConversionHistoryToCSV";

  fetch(URL_POPULATE_TABLES,{
      method: "POST",
    })
    .then((r)=>{
    if(!r.ok){
    throw new Error(`Error! status: ${r.status}`);
    }
    return r.json();
    })
    .then((data)=>{
    console.log("success")
    })
    .catch((error)=>{
    console.error("Error", error)
    })

};
  const fromCurrencySelect = document.getElementById("from_currency_list");
  const toCurrencySelect = document.getElementById("to_currency_list");

function loadCurrencyCodes() {

fetch('./converter')
    .then((r)=>{
    if(!r.ok){
    throw new Error(`Error! status: ${r.status}`);
    }
    return r.json();
    })
    .then(data=>{
      exchangeRates = data.exchangeRates;
const currencyCodes  = data.currencyCodes;

    currencyCodes.forEach(currency =>{
      const optionFrom = document.createElement('option');
      optionFrom.value = currency;
      optionFrom.textContent = currency;
      fromCurrencySelect.appendChild(optionFrom);
    });
    data.currencyCodes.forEach(currency =>{
      const optionTo = document.createElement('option');
      optionTo.value = currency;
      optionTo.textContent = currency;
      toCurrencySelect.appendChild(optionTo);
    });
  })
}

    function convertCurrency(){
  const conversionForm = document.getElementById("conversionForm");
  const result = document.getElementById("result");
  const error = document.getElementById("error");
    const amount = parseFloat(document.getElementById("amount").value);
   const fromCurrency = document.getElementById("from_currency").value;
   const toCurrency = document.getElementById("to_currency").value;
    if (!amount || !fromCurrency || !toCurrency ) {
           error.textContent = "Invalid input";
           return;
         }

         const isHomePage = window.location.pathname.includes("homePage.jsp") || window.location.pathname ==="/";
         const URL = isHomePage ? "./public_converter" : "./converter";
         fetch(URL,{
          method: 'POST',
          headers:{
      "Content-Type":"application/json"
  },
  body: JSON.stringify({
          amount: amount,
          from_currency:fromCurrency,
          to_currency:toCurrency

  })
})
   .then((r)=>{
      if(!r.ok){
          throw new Error(`Error! status: ${r.status}`);
      }
      return r.json();
      })
      .then(data=>{
             document.getElementById("result").textContent = `${amount} ${fromCurrency} = ${data.result.toFixed(2)} ${toCurrency}`;

      })
  .catch((error)=>{
              console.error("Error", error)
             document.getElementById("error").textContent = 'Failed to load'
      })
      }

       window.onload = function(){
             loadCurrencyCodes();
             loadHistory();
           }

    function loadHistory(){

    const historyContainer = document.getElementById("historyContainer");
    if(!historyContainer)return;

      if (typeof USER_ID === "undefined" || USER_ID === -1 ) {
      historyContainer.innerHTML = "<p> User not logged in </p>";
      return
     }
    fetch(`./history/${USER_ID}`)
    .then((r)=>{
    const contentType = r.headers.get("content-type")
    if(!r.ok){
    throw new Error(`Error! status: ${r.status}`);
    }
    return r.json();
    })
    .then(data=>{
     
     
          historyContainer.innerHTML = "";

          if(data.length ===0){
          historyContainer.innerHTML = "<p> No conversion history available </p>";
          return;
          }

      data.forEach((record) => {
       const historyItem = document.createElement("div");
        historyItem.classList.add("history-item");
       historyItem.innerHTML += `
        <p><strong>Conversion ID:</strong> ${record.conversionId}</p>
        <p><strong>Source Currency:</strong> ${record.sourceCurrency}</p>
        <p><strong>Target Currency:</strong> ${record.targetCurrency}</p>
        <p><strong>Operation Date:</strong> ${new Date(record.operationDate).toLocaleString()}</p>
        <p><strong>Source Amount:</strong> ${record.sourceAmount}</p>
        <p><strong>Target Amount:</strong> ${record.targetAmount}</p>
        `;
        historyContainer.appendChild(historyItem);
      });
    })
    .catch((error) =>{
      console.error("Error", error);
      document.getElementById("historyContainer").innerHTML  = "<p> Failed to load history </p>";
    });
    }


