var script = (function () {

  var add = function() {
      var word = $("#word").val();
//      var jsonValor = {"descripcion": valor};
      apiclient.addWord(word, view);
  }

  var view = function(error, res) {
	console.log(res);
    if(error != null){
    alert("Verifique los datos ingresados");
    return;
  }
    console.log(res);
    $("#ok").text(res.confirm);
  }

  return {
      add: add,
  }

})();