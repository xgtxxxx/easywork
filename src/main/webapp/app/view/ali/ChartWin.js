Ext.define('app.view.ali.ChartWin', {
	extend: 'Ext.window.Window',
    alias : 'widget.chartwin',
    width : 600,
	height : 600,
	layout : 'fit',
	frame : false,
	modal : true,
	buttonAlign : 'center',
	initComponent : function() {
		this.buttons = [{
			text : 'Close',
			scope : this,
			handler : function(){
				this.close();
			}
		}];
		this.callParent(arguments);
	}
});