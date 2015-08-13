Ext.define('app.view.ali.AliController', {
    extend: 'app.view.common.NavController',
    alias: 'controller.ali',
    
    goReport : function(){
    	var me = this.getView();
		var layout = me.ownerCt.getLayout();
		var next = me.ownerCt.down('ath4-report');
		ExtUtil.slideActive(layout,next,'r');
		next.reload({
			startMonth : me.getStartMonth(),
			endMonth : me.getEndMonth()
		});
    },
    
    goBack : function(){
    	var me = this.getView();
		var layout = me.ownerCt.getLayout();
		var next = me.ownerCt.down('ath4-detail');
		ExtUtil.slideActive(layout,next,'l');
		me.reset();
    },
    
    /**
     * for detail page;
     */
    reloadDetailData : function(){
    	var me = this.getView();
    	var start = me.getStartMonth();
    	var end = me.getEndMonth();
    	var store = me.getStore();
    	store.load({
    		params : {
    			startMonth : start,
    			endMonth : end
    		},
    	    scope: this,
    	    callback: function(records, operation, success) {
    	    }
    	});
    },
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
    
    
    /**
     * for report page
     */
    doExport : function(){
    	var grid = this.getView();
    	var params = grid.store.lastOptions.params;
    	var extr = grid.store.proxy.extraParams;
    	var startMonth = "";
    	if(extr.startMonth){
    		startMonth = extr.startMonth;
    	}
    	var endMonth = "";
    	if(extr.endMonth){
    		endMonth = extr.endMonth;
    	}
    	window.location.href = CTX.PATH+"/ali/export.do?startMonth="+startMonth+"&endMonth="+endMonth+"&field="+params.field+"&count="+params.count+"&duration="+params.duration;
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
    	var groupField = grid.getGroupField().getValue();
    	var pie = this._createPiePanel(store,groupField,'count','总询问量');
    	var win = Ext.create('app.view.ali.ChartWin',{
    		items : pie,
    		tbar  : [{
    			fieldLabel : '切换',
    			labelWidth : 30,
    			xtype : 'combo',
    			editable : false,
    			store : [ [ 'count','总询问量'], ['duration','平均通话时长'] ],
    			value : 'count',
    			queryMode : 'local',
    			listeners : {
    				change : function(combo,nv,ov){
    					win.removeAll(true);
    					win.add(me._createPiePanel(store,groupField,combo.getValue(),combo.getRawValue()));
    				}
    			}
    		}]
    	});
    	win.show();
    },
    _createPiePanel : function(store,groupField,angleField,text){
    	var pie = Ext.create('app.view.ali.chart.PieChart',{
    		store : store,
    		angleField : angleField,
    		text : text,
    		groupField : groupField
    	});
    	console.log(angleField);
    	return pie;
    },
    showColumnChart : function(){
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
    	var groupField = grid.getGroupField().getValue();
    	var column = Ext.create('app.view.ali.chart.ColumnChart',{
    		store : store,
    		groupField : groupField
    	});
    	var win = Ext.create('app.view.ali.ChartWin',{
    		layout : 'fit',
    		items : [column]
    	});
    	win.show();
    },
    reloadDataByGroup : function(searchBtn){
    	var me = this.getView();
    	var field = me.getGroupField().getValue();
    	var count = me.getCountField().getValue();
    	var duration = me.getDurationField().getValue();
    	var store = me.getStore();
    	store.load({
    		params : {
    			field : field,
    			count : count===null?0:count,
    			duration : duration===null?0:duration
    		},
    	    scope: this,
    	    callback: function(records, operation, success) {
    	    }
    	});
    }
});
