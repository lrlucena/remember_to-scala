// NAVBAR ANIMATION

jQuery(document).ready(function(){
	jQuery("#navbar li:not(.current_tab, .current_small, .current_large)").each(function() {
		jQuery(this).find("a").css('backgroundPosition', '(0 0)').css("color", "#FFFFFF");
		
		jQuery(this).mouseover(function() {
			jQuery(this).find("a").stop().animate(
				{backgroundPosition: '(0 -35px)'}, 
				{duration:150}
			).css("color", "#045F7F");
		}).mouseout(function(){
			jQuery(this).find("a").stop().animate(
				{backgroundPosition: "(0 0)"},
				{duration:150}
			).css("color", "#FFFFFF");
		});
	});
});