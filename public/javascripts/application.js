$(document).ready(function() {
    // If a table of the class list exists, add "par" to alternative tr children's class names.
	jQuery('table.list tbody > tr:nth-child(even)').addClass('par');
	jQuery('#crud-list table tbody > tr:nth-child(even)').addClass('par');
	
	
	// Adding classname to the current tab, so it can have a different color...
	var pathname = window.location.pathname;
	if (pathname.match(/^\/$|^\/home\/$/)){
		jQuery("ul#navbar li a:contains(Home)").parent()[0].className = "current_tab";
	} else if (pathname.match(/\/tasks\/*/)){
		jQuery("ul#navbar li a:contains(Tasks)").parent()[0].className = "current_tab";
	} else if (pathname.match(/\/tags\/*/)){
		jQuery("ul#navbar li a:contains(Tags)").parent()[0].className = "current_tab";
	} else if (pathname.match(/\/settings\/*/)){
		jQuery("ul#navbar li a:contains(Settings)").parent()[0].className = "current_tab";
	}


	// Adjust the size of the menu tabs (according to images).
	var items = document.getElementById('navbar').children;
	var i = 0;
	if (items != null){
		for(i = 0; i <= items.length; i++){
			if (typeof(items[i]) != "undefined"){
				if (items[i].children[0].innerHTML.length <= 10){
					if (items[i].className == "current_tab"){
						items[i].className = "current_small";
					} else {
						items[i].className = "small_tab";
					}
				} else {
					if (items[i].className == "current_tab"){
						items[i].className = "current_large";
					} else {
						items[i].className = "large_tab";
					}
				}
			}
		}
	}
});