const searchButton = document.getElementById("button");
//console.log(searchButton);
let searchValue;
//on click, searchButton will execute the specified arrow function
searchButton.addEventListener('click', () => {
    searchValue = document.getElementById("input").value;
    console.log(searchValue);
});
searchValue.addEventListener('change', () => {
    //some api call using async await
});