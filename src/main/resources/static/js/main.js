/**
*
*/

$(document).ready(function(){

    $('.newBtn, .table .editBtn').on('click', function(event){
        event.preventDefault();
        var href = $(this).attr('href');
        var text = $(this).text();
        if(text=='Edit'){
        $.get(href, function(contract, status){
            $('.myForm #id').val(contract.id)
            $('.myForm #number').val(contract.number)
            $('.myForm #name').val(contract.name)
            $('.myForm #startDate').val(contract.startDate)
            $('.myForm #endDate').val(contract.endDate)
            $('.myForm #impact').val(contract.impact)
            $('.myForm #scale').val(contract.scale)
            $('.myForm #active').val(contract.active)
            $('.myForm #description').val(contract.description)


        });

        $('.myForm #exampleModal').modal();
        }else{
            $('.myForm #id').val()
                        $('.myForm #number').val('')
                        $('.myForm #name').val('')
                        $('.myForm #startDate').val('')
                        $('.myForm #endDate').val('')
                        $('.myForm #impact').val('')
                        $('.myForm #scale').val('')
                        $('.myForm #active').val('')
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