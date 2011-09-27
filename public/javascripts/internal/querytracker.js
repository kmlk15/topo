$(document).ready(function() {

    var runQuery = function(name) {
        $.ajax({
            type    :   'POST',
            datatype:   'json',
            url     :   '/internal/QueryTracker/run',
            data    :   {
                name    :   name
            },
            timeout :   30000,
            success :   function(d) {
                // Check for empty data response
                if (typeof d === 'undefined' || d.status == 500) {
                	$('#message').html("Running " + name + " : Error");
                } else if (d.status = 200) {
                	$('#message').html("Running " + name + " : Success");
                } 
            },
            error   :   function() {
            	$('#message').html("Running " + name + " : Error");
            }
        });
    }
    
    $('#listing a').live("click", function(e) {
        e.preventDefault();
        var name = this.id;
        runQuery(name);
    });
    
});