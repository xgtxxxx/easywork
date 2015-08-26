Ext.define('app.view.common.AuthurityModel', {
    extend: 'Ext.app.ViewModel',

    alias: 'viewmodel.authurity',
    
    constructor : function() {  
        var me = this;  
        this.callParent(arguments);  
        Ext.Ajax.request({  
            url : CTX.PATH+'/sys/authurity.do', 
            params : {
            	menuId : me.mid
            },
            async : false, // 同步  
            success : function(response) {  
                var text = response.responseText;  
                var applicationInfo = Ext.decode(text, true);  
                Ext.apply(me.data, applicationInfo);  
            }  
        });  
    }
});