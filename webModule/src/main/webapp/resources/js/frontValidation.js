$(function() {
 
  $("form[name='addComputer']").validate({
    rules: {
          computerName: "required",
          company:"required",
          introduced:"required",
          discontinued:"required"
          
    },
 
    messages: {
      computerName: "SVP, Veuillez entrez le nom du computer",
      company: "Merci de sélectionnerla company de ce computer",
      introduced:"SVP, Veuillez entrez la date d'introduction du computer",
      discontinued:"SVP, Veuillez entrez la date de retrait du computer"
    },
  submitHandler: function(form) {
      form.submit();
    }
  });
});