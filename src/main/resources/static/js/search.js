const search = () => {
   let query = $("#search-input").val();
console.log(query);
   if(query==''){
    $(".search-result").hide();
   }
   else{
    console.log(query);

    let url = `https://localhost:8081/search/${query}`;

    fetch(url).then((response) =>{
        return response.json();
    }).then((data) => {
            console.log(data);
    });
    $(".search-result").show();
   }
};
