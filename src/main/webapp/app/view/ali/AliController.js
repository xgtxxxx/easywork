Ext.define('app.view.ali.AliController', {
    extend: 'app.view.common.NavController',
    alias: 'controller.ali',
    showImportWin : function(){
    	var grid = this.getView();
    	var win = Ext.create("Ext.window.Window",{
			width : 300,
			height : 150,
			title : 'Import ATH4',
			layout : 'fit',
			frame : false,
			modal : true,
			items : [{
				xtype : 'form',
				bodyPadding : '10 0 0 0',
				border: false,
				fieldDefaults : {
					labelAlign : "right",
					labelWidth : 50
				},
				items : [{
					xtype: 'filefield',
			        name: 'file',
			        fieldLabel: 'Excel',
			        allowBlank: false,
			        width : 260,
			        buttonText: 'Select excel...'
				}]
			}],
			buttonAlign : 'center',
			buttons : [{
				text : 'Save',
				handler : function(){
					var form = win.down('form').getForm();
					if (form.isValid()) {
		                form.submit({
		                	url: CTX.PATH+'/ali/import.do',
		                    success: function(form, action) {
		                       Ext.Msg.alert('Success', action.result.message);
		                       grid.getStore().reload();
		                       win.close();
		                    },
		                    failure: function(form, action) {
		                        Ext.Msg.alert('Failed', action.result ? action.result.message : 'No response');
		                    }
		                });
		            }
				}
			},{
				text : 'Cancel',
				handler : function(){
					win.close();
				}
			}]
		});
		win.show(); 
    },
    doExport : function(){
    	var grid = this.getView();
    	var selects = grid.getSelection();
    	if(selects.length===0){
    		Ext.Msg.alert("Notice","One or more items are needed!");
    		return;
    	}
    	var ids = '';
    	for(var i=0; i<selects.length; i++){
    		if(i>0){
    			ids+=',';
    		}
    		ids+=selects[i].data.id;
    	}
    	
    	window.location.href = CTX.PATH+"/ali/export.do?ids="+ids;
    },
    showPieChart : function(){
    	var me = this;
    	var grid = this.getView();
    	var selects = grid.getSelection();
    	if(selects.length===0){
    		Ext.Msg.alert("Notice","One or more items are needed!");
    		return;
    	}
    	var store = Ext.create('Ext.data.JsonStore',{
    		model: Ext.create('app.model.ali.Ath4ReportModel')
    	});
    	for(var i=0; i<selects.length; i++){
    		store.add(selects[i]);
    	}
    	var pie = this._createPiePanel(store,'totalCount','总询问量');
    	var win = Ext.create('app.view.ali.ChartWin',{
    		items : pie,
    		tbar  : [{
    			xtype : 'label',
    			text  : 'Pie Chart'
    		},'-',{
    			fieldLabel : '切换',
    			labelWidth : 30,
    			xtype : 'combo',
    			editable : false,
    			store : [ [ 'totalCount','总询问量'], ['avgDuration','平均通话时长'] ],
    			value : '总询问量',
    			queryMode : 'local',
    			listeners : {
    				change : function(combo,nv,ov){
    					win.removeAll(true);
    					win.add(me._createPiePanel(store,combo.getValue(),combo.getRawValue()));
    				}
    			}
    		}]
    	});
    	win.show();
    },
    _createPiePanel : function(store,angleField,text){
    	var pie = Ext.create('app.view.ali.chart.PieChart',{
    		store : store,
    		angleField : angleField,
    		text : text
    	});
    	
    	return pie;
    }
});
