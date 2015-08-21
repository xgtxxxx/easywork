Ext.define('app.view.common.NavController', {
    extend: 'Ext.app.ViewController',
    alias: 'controller.nav',
    addActivePanel : function(tree, record){
    	if(record.data.leaf===true){
    		var tab = this.getView().down('maintab');
    		var panel = tab.child(record.data.xtype);
    		if (!panel) {
    			panel = Ext.create(record.data.clazz, {
    				title : record.data.text,
    				closable : tab.items.length>0
    			});
    			tab.add(panel);
    		}
    		tab.setActiveTab(panel);
    	}
    }
});
