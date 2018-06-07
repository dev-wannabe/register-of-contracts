/**
*
*/

$(document).ready(function(){

    $('.table .editBtn').on('click', function(event){
        event.preventDefault();
        var href = $(this).attr('href');
        var text = $(this).text();
        if(text=='Edit'){
        $.get(href, function(description, status){
            $('.myForm #id').val(description.id)
            $('.myForm #description').val(description.description)

        });

        $('.myForm #exampleModal').modal();
        }else{
            $('.myForm #id').val()
 //                       $('.myForm #name').val()
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