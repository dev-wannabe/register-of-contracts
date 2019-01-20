

$(document).ready(function(){

    $('.newBtn, .table .editBtn').on('click', function(event) {
        event.preventDefault();
        var href = $(this).attr('href');
        var text = $(this).text();
        if(text=='Edit'){
        $.get(href, function(contract, status) {
            $('.editForm #id').val(contract.id)
            $('.editForm #number').val(contract.number)
            $('.editForm #name').val(contract.name)
            $('.editForm #startDate').val(contract.startDate)
            $('.editForm #endDate').val(contract.endDate)
            $('.editForm #income').val(contract.income)
            $('.editForm #description').val(contract.description)
        });

        $('.editForm #exampleModal').modal();
        } else {

                        $('.editForm #id').val('')
                        $('.editForm #number').val('')
                        $('.editForm #name').val('')
                        $('.editForm #startDate').val('')
                        $('.editForm #endDate').val('')
                        $('.editForm #income').val('')
                        $('.editForm #description').val('')
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