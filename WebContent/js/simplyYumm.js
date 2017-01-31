

$( document ).ready(function() {
    console.log( "ready!" );
      var    url = window.location.href;
    if(url.includes("featured")){
            console.log( "ready to go!" );
    var recipeid = getParameterByName('recipeid'); 
        console.log(recipeid);
        $.getJSON("/SimplyYum/get/Recipes.htm?recipe_id="+recipeid, function( data ) {
   $("#title").text(data.title);
        var i=0;
            if(data.image_url!=null){
                $(".img-recipe").attr("src",data.image_url);
            }else{
               $(".img-recipe").hide(); 
            }
        for (i = 0; i < data.ingredients.length; i++) {
            if(data.ingredients[i]!="")
            $(".ingredients").append('<li>'+data.ingredients[i]+'</li>');
        }
        for (i = 0; i < data.instructions.length; i++) {
            if(data.instructions[i]!="")
            $(".directions").append('<li>'+data.instructions[i]+'</li>');
        }
});
      
    }else  if(url.includes("recipes")){
        var ingre  = getParameterByName('ingredients'); 
        if(ingre!=null){
        
         $.ajax({
    url: "/SimplyYum/get/Recipes.htm",
    data:  {ingredients: JSON.parse(ingre)
  },
    traditional: true,
    success: function(result) {
        
    populate(result);    
        
        
console.log(result.length)    }
});
}
        else{
             $.ajax({
    url: "/SimplyYum/get/Recipes.htm",
    data:  {ingredients: 'none'
  },
    traditional: true,
    success: function(result) {
    populate(result);     
        
        
        
        
console.log(result.length)    }
});
        }
        
    }

});
function populate(result){
    for(i=0;i<result.length;i++){
        
        var href="/SimplyYum/featured.html?recipeid="+result[i].recipe_no;
var string="<li>";
if(result[i].image_url!=null&&result[i].image_url!=""){

    string =string+"<a href='"+href+"'><img src='"+result[i].image_url+"' width='330' height='165' alt='Image'></a><div><h3><a href='"+href+"' class='title'>"+result[i].title+"</a></h3>";
}else{
string =string+"<div><h3><a href='"+href+"' class='title'><b>"+result[i].title+"</a></b></h3>";
}

if(result[i].category!=null&&result[i].category!="")
string =string+"<h4><b>Category</b>:"+result[i].category+"</h4>";
if(result[i].core_ingr!=null&&result[i].core_ingr!="")
string =string+"<h4><b>Core Ingredients</b>:"+result[i].core_ingr+"</h4>";
if(result[i].veg!=null&&result[i].veg!="")
string =string+"<h4><b>Vegetables</b>:"+result[i].veg.split(',').join(', ')+"</h4>";
string =string+"</div></li>";
                    $(".search-list").append(string);

    }
}
function getParameterByName(name, url) {
    if (!url) {
      url = window.location.href;
    }
    name = name.replace(/[\[\]]/g, "\\$&");
    var regex = new RegExp("[?&]" + name + "(=([^&#]*)|&|#|$)"),
        results = regex.exec(url);
    if (!results) return null;
    if (!results[2]) return '';
    return decodeURIComponent(results[2].replace(/\+/g, " "));
}

    var availableTags = [
      "ActionScript",
      "AppleScript",
      "Asp",
      "BASIC",
      "C",
      "C++",
      "Clojure",
      "COBOL",
      "ColdFusion",
      "Erlang",
      "Fortran",
      "Groovy",
      "Haskell",
      "Java",
      "JavaScript",
      "Lisp",
      "Perl",
      "PHP",
      "Python",
      "Ruby",
      "Scala",
      "Scheme"
    ];
    $( "#search" ).autocomplete({
      source: availableTags
    });
  

function queryRecipe(){
    
    alert('Work in Progess');
}
$("#buid-recipe").submit(function(){
/*
    alert('BUILD RECIPE!');
*/
    var ingredients = [];
    $( "#sortable2 li" ).each(function( index ) {
/*
  console.log( index + ": " + $( this ).text() );
*/
        ingredients.push($( this ).text() );
});
    
    console.log(ingredients);
    var hours=$("#hours").val();
    var mins=$("#mins").val();
    console.log("hours"+hours+"mins"+mins);

   /*  $.getJSON("/SimplyYum/get/Recipes.htm", data: {
    hours:hours,
    mins: mins,
    ingredients: ingredients
  })
    .done(function( data ) {
         console.log("recipe");
      console.log(data);
    });*/
  var  para = JSON.stringify(ingredients);

    location.href="/SimplyYum/recipes.html?ingredients="+para;
   /* $.ajax({
    url: "/SimplyYum/get/Recipes.htm",
    data:  {hours:hours,mins: mins,ingredients: ingredients
  },
    traditional: true,
    success: function(result) {
console.log(result.da)    }
});*/
    return false;
});
   
    


/* $( "#ingr" ).autocomplete({
      source: availableTags,
        select: function( event, ui ) {
          $("#sortable2").append('<li class="ui-state-highlight">'+ui.item.label+'</li>');
          return true;
        }
    });*/
 

function queryRecipe(){
    
    alert('Work in Progess');
}
        $(function() {
$( ".sortable_list" ).sortable({
    connectWith: ".connectedSortable",
    /*stop: function(event, ui) {
        var item_sortable_list_id = $(this).attr('id');
        console.log(ui);
        //alert($(ui.sender).attr('id'))
    },*/
    receive: function(event, ui) {
       /* alert("dropped on = "+this.id); // Where the item is dropped
          alert("sender = "+ui.sender[0].id); // Where it came from
          alert("item = "+ui.item[0].innerHTML); //Which item (or ui.item[0].id)*/
    }         
}).disableSelection();
    

});




     var cache = {};

    $( "#ingr" )
      // don't navigate away from the field on tab when selecting an item
      .on( "keydown", function( event ) {
        if ( event.keyCode === $.ui.keyCode.TAB &&
            $( this ).autocomplete( "instance" ).menu.active ) {
          event.preventDefault();
        }
      })
      .autocomplete({
        minLength: 0,
        source: function( request, response ) {
          // delegate back to autocomplete, but extract the last term
    /*    $.ajax( {
          url: "/SimplyYum/get/Ingredients.htm?keyword=all",
          dataType: "json",
          data: {
            
          },
          success: function( data ) {
            response(data);
          }
        } );*/
             var term = request.term;
        if ( term in cache ) {
          response( cache[ term ] );
          return;
        }
 
        $.getJSON( "/SimplyYum/get/Ingredients.htm?keyword="+term, request, function( data, status, xhr ) {
          cache[ term ] = data;
          response( data );
        });
      
   
            
         /*   $.ui.autocomplete.filter(
            data, extractLast( request.term )*/
            
          
        },
        select: function( event, ui ) {
          $("#sortable2").append('<li class="ui-state-highlight">'+ui.item.label+'</li>');
          return true;
        }
      });



