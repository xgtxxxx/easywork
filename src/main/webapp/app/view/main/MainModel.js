/**
 * This class is the view model for the Main view of the application.
 */
Ext.define('app.view.main.MainModel', {
    extend: 'Ext.app.ViewModel',

    alias: 'viewmodel.main',

    data: {
    	name : 'app',  
    	  
        // 系统信息  
        system : {  
            name : 'EasyWork System',  
            version : '5.2014.06.60',  
            iconUrl : ''  
        }
    },
    
    getSysmenus : function(){
    	var menus = [];
    	Ext.Ajax.request({
    		async : false,
			url : CTX.PATH + '/app/mock/sysmunu.json',
			success : function(response) {
				var res = Ext.decode(response.responseText);
				menus = res.sysmenu;
			}
		});
    	return menus;
    }
});