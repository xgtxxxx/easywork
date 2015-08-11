Ext.define('app.view.portal.PortalTemplate', {
    extend: 'Ext.Component',
    requires : [
   	         'app.view.portal.PortalTemplate'
   	         ],
    alias: 'widget.portal-template',
    initComponent : function() {  
    	this.loader = {
            url: this.feedUrl,
            autoLoad: true
        }
    	this.callParent();  
    }
});
