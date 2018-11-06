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
            $('.editForm #id').val(contract.id)
            $('.editForm #number').val(contract.number)
            $('.editForm #name').val(contract.name)
            $('.editForm #startDate').val(contract.startDate)
            $('.editForm #endDate').val(contract.endDate)
            $('.editForm #impact').val(contract.impact)
            $('.editForm #scale').val(contract.scale)
            $('.editForm #active').val(contract.active)
            $('.editForm #description').val(contract.description)
        });

        $('.editForm #exampleModal').modal();
        }else{
            $('.editForm #id').val('')

                        $('.editForm #number').val('')
                        $('.editForm #name').val('')
                        $('.editForm #startDate').val('')
                        $('.editForm #endDate').val('')
                        $('.editForm #impact').val('')
                        $('.editForm #scale').val('')
                        $('.editForm #active').val('')
                        $('.editForm #description').val('No description available, yet')

                          $('.editForm #exampleModal').modal();
        }
    });

    $('.table .delBtn').on('click', function(event){
        event.preventDefault();
        var href = $(this).attr('href');
        $('#myModal #delRef').attr('href', href );
        $('#myModal').modal();

    });

});