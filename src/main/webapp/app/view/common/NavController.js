Ext.define('app.view.common.NavController', {
    extend: 'Ext.app.ViewController',

    switchTabPanel : function(t, td, cellIndex, record, tr, rowIndex, e, eOpts){
    	var tab = this.getView().child('tabpanel');
    	var panel = tab.child(record.data.xtype);
		if (!panel) {
			panel = Ext.create(record.data.clazz, {
				title : record.data.text,
				closable : true
			});
			panel.reload();
			tab.add(panel);
		}
		tab.setActiveTab(panel);
    }
});
