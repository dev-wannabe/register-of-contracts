/**
*
*/

$(document).ready(function(){

    $('.table .editDescBtn').on('click', function(event){
        event.preventDefault();
        var href = $(this).attr('href');
        var text = $(this).text();
        if(text=='Edit'){
        $.get(href, function(contract, status){
            $('.myForm #id').val(contract.description.id)
            $('.myForm #description').val(contract.description.description)

        });

        $('.myForm #exampleModal').modal();
        }else{
            $('.myForm #id').val()
//                        $('.myForm #name').val()
//                        $('.myForm #startDate').val()
//                        $('.myForm #endDate').val()
                        $('.myForm #id').val('')
                        $('.myForm #description').val('')

                          $('.myForm #exampleModal').modal();
        }
    });

    $('.table .delBtn').on('click', function(event){
        event.preventDefault();
        var href = $(this).attr('href');
        $('#myModal #delRef').attr('href', href );
        $('#myModal').modal();

    });

});