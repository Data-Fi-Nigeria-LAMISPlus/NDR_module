(this.webpackJsonpLamisPlus=this.webpackJsonpLamisPlus||[]).push([[0],{539:function(e,t,a){},540:function(e,t,a){},636:function(e,t,a){"use strict";a.r(t);var r=a(1),i=a.n(r),n=a(21),c=a.n(n),o=a(41),l=a(35),s=(a(364),a(539),a(540),a(541),a(11)),b=a(13),d=a(5),u=a(450),j=a(692),f=a(706),O=a(691),h=a(117),p=a(369),g=a(242),x=a(114),v=a(27),m=a(171),y=a(638),w=a(639),S=a(681),B=a(452),P=a(680),_=a(491),A=a(71),C=a.n(A),k=new URLSearchParams(window.location.search).get("jwt"),L="/api/v1/",R=a(677),N=a(678),z=a(705),F=a(679),T=a(379),D=a(451),I=a(283),M=a.n(I),E=a(707),W=a(682),H=(a(560),a(7)),U=Object(u.a)((function(e){return{root:{width:"100%",maxWidth:360,backgroundColor:e.palette.background.paper,"& > * + *":{marginTop:e.spacing(2)}}}}));function G(e){var t=U(),a=Object(r.useState)([]),n=Object(s.a)(a,2),c=n[0],o=n[1],b=Object(r.useState)(!1),d=Object(s.a)(b,2),u=d[0],j=d[1],f=Object(r.useState)({facilities:[]}),O=Object(s.a)(f,2),h=O[0],p=(O[1],Object(r.useState)(!1)),g=Object(s.a)(p,2),A=g[0],I=g[1],G=i.a.useState([]),V=Object(s.a)(G,2),q=V[0],X=V[1],J=Object(r.useState)(null),K=Object(s.a)(J,2),Q=(K[0],K[1]),Y=Object(r.useState)(!1),Z=Object(s.a)(Y,2),$=Z[0],ee=Z[1];function te(){return(te=Object(m.a)(Object(x.a)().mark((function e(){return Object(x.a)().wrap((function(e){for(;;)switch(e.prev=e.next){case 0:C.a.get("".concat(L,"account"),{headers:{Authorization:"Bearer ".concat(k)}}).then((function(e){Q(e.data),o(e.data.applicationUserOrganisationUnits)})).catch((function(e){}));case 1:case"end":return e.stop()}}),e)})))).apply(this,arguments)}Object(r.useEffect)((function(){!function(){te.apply(this,arguments)}()}),[]);var ae=function(e){return function(){var t=q.indexOf(e),a=Object(v.a)(q);-1===t?a.push(e):a.splice(t,1),X(a)}},re=function(e){I(!A)};return Object(H.jsxs)("div",{children:[Object(H.jsx)(l.a,{autoClose:3e3,hideProgressBar:!0}),Object(H.jsx)(R.a,{children:Object(H.jsxs)(N.a,{children:[q.length>=1?Object(H.jsx)(H.Fragment,{children:Object(H.jsxs)(D.a,{color:"primary",variant:"contained",className:" float-right mr-1",size:"large",hidden:u,onClick:function(){return function(){j(!0),ee(!0);var t="";q.forEach((function(e){var a=e.organisationUnitId;t="facilityIds="+a})),h.facilities=t,C.a.get("".concat(L,"ndr/generate?").concat(t,"&isInitial=").concat(A),{headers:{Authorization:"Bearer ".concat(k)}}).then((function(t){window.setTimeout((function(){l.b.success(" Generating NDR Successful!"),ee(!1),e.setValue(1)}),5e3)})).catch((function(e){if(ee(!1),j(!1),l.b.error(" Something went wrong! Please contact administrator."),e.response&&e.response.data){var t=e.response.data.apierror&&""!==e.response.data.apierror.message?e.response.data.apierror.message:"Something went wrong, please try again";l.b.error(t)}else l.b.error("Something went wrong. Please try again...")}))}()},children:[Object(H.jsx)(T.a,{})," \xa0\xa0",Object(H.jsx)("span",{style:{textTransform:"capitalize"},children:" Generate Messages"})]})}):Object(H.jsx)(H.Fragment,{children:Object(H.jsxs)(D.a,{color:"primary",variant:"contained",className:" float-right mr-1",size:"large",disabled:"true",children:[Object(H.jsx)(T.a,{})," \xa0\xa0",Object(H.jsx)("span",{style:{textTransform:"capitalize"},children:" Generate Messages "})]})}),Object(H.jsxs)(H.Fragment,{children:[Object(H.jsx)("br",{})," ",Object(H.jsx)("br",{}),Object(H.jsxs)(z.a,{severity:"info",children:[Object(H.jsx)(F.a,{children:"Info"}),"Please check the Facilities you want"]}),Object(H.jsx)("br",{}),Object(H.jsxs)("label",{children:[Object(H.jsx)("input",{type:"radio",name:"status",checked:!1===A,onChange:re}),Object(H.jsx)("b",{children:" Updated"})]}),"   ","   ",Object(H.jsxs)("label",{children:[Object(H.jsx)("input",{type:"radio",name:"status",checked:!0===A,onChange:re}),Object(H.jsx)("b",{children:" Initial"})]}),Object(H.jsx)("br",{}),Object(H.jsxs)(y.a,{dense:!0,className:t.root,children:[Object(H.jsx)("br",{}),c.map((function(e){var t="checkbox-list-secondary-label-".concat(e.id);return Object(H.jsxs)(w.a,{button:!0,children:[Object(H.jsx)(P.a,{children:Object(H.jsx)(M.a,{})}),Object(H.jsx)(B.a,{id:t,primary:"".concat(e.organisationUnitName)}),Object(H.jsx)(S.a,{children:Object(H.jsx)(_.a,{edge:"end",onChange:ae(e),checked:-1!==q.indexOf(e),inputProps:{"aria-labelledby":t}})})]},e.id)}))]})]})]})}),Object(H.jsx)(E.a,{isOpen:$,toggle:function(){return ee(!$)},backdrop:!1,fade:!0,style:{marginTop:"250px"},size:"lg",children:Object(H.jsx)(W.a,{children:Object(H.jsx)("h2",{children:"Generating NDR File. Please wait..."})})})]})}var V=a(230),q=a.n(V),X=a(504),J=a.n(X),K=a(304),Q=a(501),Y=a.n(Q),Z=(a(482),a(704)),$=a(708),ee=a(703),te=a(508),ae=a(292),re=a.n(ae),ie=a(301),ne=a.n(ie),ce=a(293),oe=a.n(ce),le=a(299),se=a.n(le),be=a(198),de=a.n(be),ue=a(197),je=a.n(ue),fe=a(238),Oe=a.n(fe),he=a(294),pe=a.n(he),ge=a(296),xe=a.n(ge),ve=a(297),me=a.n(ve),ye=a(298),we=a.n(ye),Se=a(302),Be=a.n(Se),Pe=a(295),_e=a.n(Pe),Ae=a(300),Ce=a.n(Ae),ke=a(303),Le=a.n(ke),Re=a(502),Ne=a.n(Re),ze={Add:Object(r.forwardRef)((function(e,t){return Object(H.jsx)(re.a,Object(b.a)(Object(b.a)({},e),{},{ref:t}))})),Check:Object(r.forwardRef)((function(e,t){return Object(H.jsx)(oe.a,Object(b.a)(Object(b.a)({},e),{},{ref:t}))})),Clear:Object(r.forwardRef)((function(e,t){return Object(H.jsx)(je.a,Object(b.a)(Object(b.a)({},e),{},{ref:t}))})),Delete:Object(r.forwardRef)((function(e,t){return Object(H.jsx)(Oe.a,Object(b.a)(Object(b.a)({},e),{},{ref:t}))})),DetailPanel:Object(r.forwardRef)((function(e,t){return Object(H.jsx)(de.a,Object(b.a)(Object(b.a)({},e),{},{ref:t}))})),Edit:Object(r.forwardRef)((function(e,t){return Object(H.jsx)(pe.a,Object(b.a)(Object(b.a)({},e),{},{ref:t}))})),Export:Object(r.forwardRef)((function(e,t){return Object(H.jsx)(_e.a,Object(b.a)(Object(b.a)({},e),{},{ref:t}))})),Filter:Object(r.forwardRef)((function(e,t){return Object(H.jsx)(xe.a,Object(b.a)(Object(b.a)({},e),{},{ref:t}))})),FirstPage:Object(r.forwardRef)((function(e,t){return Object(H.jsx)(me.a,Object(b.a)(Object(b.a)({},e),{},{ref:t}))})),LastPage:Object(r.forwardRef)((function(e,t){return Object(H.jsx)(we.a,Object(b.a)(Object(b.a)({},e),{},{ref:t}))})),NextPage:Object(r.forwardRef)((function(e,t){return Object(H.jsx)(de.a,Object(b.a)(Object(b.a)({},e),{},{ref:t}))})),PreviousPage:Object(r.forwardRef)((function(e,t){return Object(H.jsx)(se.a,Object(b.a)(Object(b.a)({},e),{},{ref:t}))})),ResetSearch:Object(r.forwardRef)((function(e,t){return Object(H.jsx)(je.a,Object(b.a)(Object(b.a)({},e),{},{ref:t}))})),Search:Object(r.forwardRef)((function(e,t){return Object(H.jsx)(Ce.a,Object(b.a)(Object(b.a)({},e),{},{ref:t}))})),SortArrow:Object(r.forwardRef)((function(e,t){return Object(H.jsx)(ne.a,Object(b.a)(Object(b.a)({},e),{},{ref:t}))})),ThirdStateCheck:Object(r.forwardRef)((function(e,t){return Object(H.jsx)(Be.a,Object(b.a)(Object(b.a)({},e),{},{ref:t}))})),ViewColumn:Object(r.forwardRef)((function(e,t){return Object(H.jsx)(Le.a,Object(b.a)(Object(b.a)({},e),{},{ref:t}))}))},Fe=Object(u.a)((function(e){return{root:{width:"100%",maxWidth:360,backgroundColor:e.palette.background.paper,"& > * + *":{marginTop:e.spacing(2)}}}}));function Te(){Fe();var e=Object(r.useState)([]),t=Object(s.a)(e,2),a=t[0],i=t[1],n=Object(r.useState)(""),c=Object(s.a)(n,2),o=c[0],b=(c[1],Object(r.useState)(!1)),d=Object(s.a)(b,2),u=d[0],j=d[1],f=function(){return j(!u)};Object(r.useEffect)((function(){O()}),[]);function O(){return h.apply(this,arguments)}function h(){return(h=Object(m.a)(Object(x.a)().mark((function e(){return Object(x.a)().wrap((function(e){for(;;)switch(e.prev=e.next){case 0:C.a.get("".concat(L,"ndr/files"),{headers:{Authorization:"Bearer ".concat(k)}}).then((function(e){i(e.data)})).catch((function(e){}));case 1:case"end":return e.stop()}}),e)})))).apply(this,arguments)}return Object(H.jsxs)("div",{children:[Object(H.jsx)(l.a,{autoClose:3e3,hideProgressBar:!0}),Object(H.jsx)(D.a,{variant:"contained",color:"primary",className:" float-right",startIcon:Object(H.jsx)(K.a,{size:"10"}),style:{backgroundColor:"#014d88"},href:"https://ndr.phis3project.org.ng/Identity/Account/Login?ReturnUrl=%2F",target:"_blank",children:Object(H.jsx)("span",{children:" NDR Portal"})}),Object(H.jsx)("br",{}),Object(H.jsx)("br",{}),Object(H.jsx)(q.a,{icons:ze,title:"List of Files Generated",columns:[{title:"Facility Name",field:"name",filtering:!1},{title:"Number of Files Generated",field:"files",filtering:!1},{title:"File Name",field:"fileName",filtering:!1},{title:"Date Last Generated",field:"date",type:"date",filtering:!1},{title:"Action",field:"actions",filtering:!1}],isLoading:o,data:a.map((function(e){return{name:e.facility,files:e.files,fileName:e.fileName,date:Ne()(e.lastModified).format("LLLL"),ndrStatus:Object(H.jsx)(te.a,{now:e.percentagePushed,variant:(t=e.percentagePushed,console.log(t),t<=20?"danger":t>20&&t<=69?"warning":t>=70&&t<=99?"info":"success"),label:"".concat(e.percentagePushed,"%")}),actions:Object(H.jsx)("div",{children:Object(H.jsx)(Z.a.Menu,{position:"right",children:Object(H.jsx)(Z.a.Item,{children:Object(H.jsx)($.a,{style:{backgroundColor:"rgb(153,46,98)"},primary:!0,children:Object(H.jsx)(ee.a,{item:!0,text:"Action",children:Object(H.jsx)(ee.a.Menu,{style:{marginTop:"10px"},children:Object(H.jsxs)(ee.a.Item,{onClick:function(){return t=e.fileName,void C.a.get("".concat(L,"ndr/download/").concat(t),{headers:{Authorization:"Bearer ".concat(k)},responseType:"blob"}).then((function(e){var a=e.data,r=new Blob([a],{type:"application/octet-stream"});Y.a.saveAs(r,"".concat(t,".zip"))})).catch((function(e){}));var t},children:[Object(H.jsx)(J.a,{color:"primary"})," Download File"]})})})})})})})};var t})),options:{pageSizeOptions:[5,10,50,100,150,500],headerStyle:{backgroundColor:"#014d88",color:"#fff",margin:"auto"},filtering:!0,searchFieldStyle:{width:"300%",margingLeft:"250px"},exportButton:!1,searchFieldAlignment:"left"}}),Object(H.jsx)(E.a,{isOpen:u,toggle:f,backdrop:!1,fade:!0,style:{marginTop:"250px"},size:"lg",children:Object(H.jsx)(W.a,{children:Object(H.jsx)("h1",{children:"Uploading File To NDR. Please wait..."})})})]})}var De=a(693),Ie=a(694),Me=a(701),Ee=function(e){var t=Object(r.useState)([]),a=Object(s.a)(t,2),n=a[0],c=a[1],o=i.a.useState([]),b=Object(s.a)(o,2),d=b[0],u=b[1],j=Object(r.useState)(!1),f=Object(s.a)(j,2),O=(f[0],f[1]),h=Object(r.useState)(!1),p=Object(s.a)(h,2),g=p[0],A=p[1],z=Object(r.useState)({facilities:[]}),F=Object(s.a)(z,2),T=F[0];F[1];function I(){return(I=Object(m.a)(Object(x.a)().mark((function e(){return Object(x.a)().wrap((function(e){for(;;)switch(e.prev=e.next){case 0:C.a.get("".concat(L,"account"),{headers:{Authorization:"Bearer ".concat(k)}}).then((function(e){c(e.data.applicationUserOrganisationUnits)})).catch((function(e){}));case 1:case"end":return e.stop()}}),e)})))).apply(this,arguments)}Object(r.useEffect)((function(){!function(){I.apply(this,arguments)}()}),[]);var E=function(e){return function(){var t=d.indexOf(e),a=Object(v.a)(d);-1===t?a.push(e):a.splice(t,1),u(a)}};return Object(H.jsx)(R.a,{children:Object(H.jsxs)(N.a,{children:[Object(H.jsx)(D.a,{color:"primary",variant:"contained",className:" float-right mr-1",size:"large",onClick:function(){return function(){A(!0),O(!0);var t="";d.forEach((function(e){var a=e.organisationUnitId;t="facilityIds="+a})),console.log(t),T.facilities=t,C.a.get("".concat(L,"ndr/recapture/generate?").concat(t),{headers:{Authorization:"Bearer ".concat(k)}}).then((function(t){window.setTimeout((function(){l.b.success(" Generating Biometrics XML Successful!"),O(!1),e.setValue(1)}),5e3)})).catch((function(e){if(O(!1),A(!1),l.b.error(" Something went wrong! Please contact administrator."),e.response&&e.response.data){var t=e.response.data.apierror&&""!==e.response.data.apierror.message?e.response.data.apierror.message:"Something went wrong, please try again";l.b.error(t)}else l.b.error("Something went wrong. Please try again...")}))}()},children:Object(H.jsxs)("span",{style:{textTransform:"capitalize"},children:[" ","Generate Biometrics XML"," "]})}),Object(H.jsx)("br",{}),g?Object(H.jsx)(Me.a,{}):"",n.map((function(e){var t="checkbox-list-secondary-label-".concat(e.id);return Object(H.jsxs)(De.a,{children:[Object(H.jsx)(Ie.a,{children:Object(H.jsx)(y.a,{children:Object(H.jsxs)(w.a,{children:[Object(H.jsx)(P.a,{children:Object(H.jsx)(M.a,{})}),Object(H.jsx)(B.a,{id:t,primary:"".concat(e.organisationUnitName)}),Object(H.jsx)(S.a,{children:Object(H.jsx)(_.a,{edge:"end",onChange:E(e),checked:-1!==d.indexOf(e),inputProps:{"aria-labelledby":t}})})]},e.id)},e.id)}),Object(H.jsx)(Ie.a,{}),Object(H.jsx)(Ie.a,{}),Object(H.jsx)(Ie.a,{})]})}))]})})};a(702),a(12),a(695),a(696),a(697),a(698),a(699),a(700),Object(u.a)((function(e){return{card:{margin:e.spacing(20),display:"flex",flexDirection:"column",alignItems:"center"},form:{width:"100%",marginTop:e.spacing(3)},submit:{margin:e.spacing(3,0,2)},cardBottom:{marginBottom:20},Select:{height:45,width:350},button:{margin:e.spacing(1)},root:{"& > *":{margin:e.spacing(1)}},input:{display:"none"},error:{color:"#f85032",fontSize:"11px"},success:{color:"#4BB543 ",fontSize:"11px"}}}));var We=["children","value","index"];function He(e){var t=e.children,a=e.value,r=e.index,i=Object(d.a)(e,We);return Object(H.jsx)(h.a,Object(b.a)(Object(b.a)({component:"div",role:"tabpanel",hidden:a!==r,id:"scrollable-force-tabpanel-".concat(r),"aria-labelledby":"scrollable-force-tab-".concat(r)},i),{},{children:a===r&&Object(H.jsx)(p.a,{p:5,children:t})}))}function Ue(e){return{id:"scrollable-force-tab-".concat(e),"aria-controls":"scrollable-force-tabpanel-".concat(e)}}var Ge=Object(u.a)((function(e){return{root2:{flexGrow:1,width:"100%",backgroundColor:e.palette.background.paper,margin:e.spacing(7),bullet:{display:"inline-block",margin:"0 2px",transform:"scale(0.8)"},title:{fontSize:12},pos:{fontSize:11},cardContent:{padding:2},cardroot:{margin:e.spacing(1),height:"250px !important"}},alertmsge:{marginTop:e.spacing(2)},rootaccordia:{width:"100%"},accordiaheading:{fontSize:e.typography.pxToRem(15),fontWeight:e.typography.fontWeightRegular},allergiesroot:{display:"flex",justifyContent:"center",flexWrap:"wrap","& > *":{margin:e.spacing(.5)}},checkboxroot:{display:"flex"},formControl:{margin:e.spacing(3)},root:{"& .MuiTextField-root":{margin:e.spacing(1),width:200}},formroot:{"& .MuiTextField-root":{margin:e.spacing(1),width:200}},heading:{fontSize:e.typography.pxToRem(15)},secondaryHeading:{fontSize:e.typography.pxToRem(15),color:e.palette.text.secondary},icon:{verticalAlign:"bottom",height:20,width:20},details:{alignItems:"center"},column:{flexBasis:"33.33%"},helper:{borderLeft:"2px solid ".concat(e.palette.divider),padding:e.spacing(1,2)},link:{color:e.palette.primary.main,textDecoration:"none","&:hover":{textDecoration:"underline"}},inforoot:{width:"95%",margin:20,backgroundColor:"#eee"}}})),Ve=function(e){var t=Ge(),a=Object(r.useState)(0),i=Object(s.a)(a,2),n=i[0],c=i[1];!function(e,t){var a=t,r=new RegExp("[?&]"+e+"=([^&#]*)","i").exec(a);r&&r[1]}("tab",e.location);Object(r.useEffect)((function(){}),[n]);return Object(H.jsxs)("div",{className:t.root,children:[Object(H.jsx)("div",{className:"row page-titles mx-0",style:{marginTop:"0px",marginBottom:"-10px"},children:Object(H.jsx)("ol",{className:"breadcrumb",children:Object(H.jsx)("li",{className:"breadcrumb-item active",children:Object(H.jsx)("h4",{children:"NDR"})})})}),Object(H.jsx)("br",{}),Object(H.jsxs)(j.a,{position:"static",style:{backgroundColor:"#fff"},children:[Object(H.jsxs)(f.a,{value:n,onChange:function(e,t){c(t)},variant:"scrollable",scrollButtons:"on",indicatorColor:"secondary",textColor:"primary","aria-label":"scrollable force tabs example",children:[Object(H.jsx)(O.a,Object(b.a)({className:t.title,label:"Generate Messages ",icon:Object(H.jsx)(g.b,{})},Ue(0))),Object(H.jsx)(O.a,Object(b.a)({className:t.title,label:"Download Files",icon:Object(H.jsx)(g.a,{})},Ue(1))),Object(H.jsx)(O.a,Object(b.a)({className:t.title,label:"NDR COnfiguration",icon:Object(H.jsx)(g.a,{})},Ue(2))),Object(H.jsx)(O.a,Object(b.a)({className:t.title,label:"Biometrics XML",icon:Object(H.jsx)(g.a,{})},Ue(3)))]}),Object(H.jsx)("div",{})]}),Object(H.jsx)(He,{value:n,setValue:c,index:0,children:Object(H.jsx)(G,{value:n,setValue:c})}),Object(H.jsx)(He,{value:n,setValue:c,index:1,children:Object(H.jsx)(Te,{value:n,setValue:c})}),Object(H.jsx)(He,{value:n,setValue:c,index:3,children:Object(H.jsx)(Ee,{value:n,setValue:c})})]})};function qe(){return Object(H.jsx)(o.a,{children:Object(H.jsxs)("div",{children:[Object(H.jsx)(l.a,{}),Object(H.jsx)(o.d,{children:Object(H.jsx)(o.b,{path:"/",children:Object(H.jsx)(Ve,{})})})]})})}var Xe=a(377),Je=function(e){e&&e instanceof Function&&a.e(6).then(a.bind(null,879)).then((function(t){var a=t.getCLS,r=t.getFID,i=t.getFCP,n=t.getLCP,c=t.getTTFB;a(e),r(e),i(e),n(e),c(e)}))},Ke=a(506),Qe="ltr",Ye=[{typography:"poppins",version:"light",layout:"vertical",headerBg:"color_1",navheaderBg:"color_1",sidebarBg:"color_1",sidebarStyle:"full",sidebarPosition:"fixed",headerPosition:"fixed",containerLayout:"full",direction:Qe},{typography:"poppins",version:"light",layout:"vertical",primary:"color_5",headerBg:"color_5",navheaderBg:"color_1",sidebarBg:"color_1",sidebarStyle:"full",sidebarPosition:"fixed",headerPosition:"fixed",containerLayout:"wide",direction:Qe},{typography:"poppins",version:"light",layout:"vertical",navheaderBg:"color_11",headerBg:"color_1",sidebarBg:"color_11",sidebarStyle:"full",sidebarPosition:"fixed",headerPosition:"fixed",containerLayout:"wide",primary:"color_11",direction:Qe},{typography:"poppins",version:"dark",layout:"vertical",headerBg:"color_3",navheaderBg:"color_3",sidebarBg:"color_1",sidebarStyle:"full",sidebarPosition:"fixed",headerPosition:"fixed",containerLayout:"wide",primary:"color_1",direction:Qe},{typography:"poppins",version:"light",layout:"vertical",navheaderBg:"color_15",headerBg:"color_1",sidebarStyle:"full",sidebarBg:"color_1",sidebarPosition:"fixed",headerPosition:"fixed",containerLayout:"wide",primary:"color_15",direction:Qe},{typography:"poppins",version:"light",layout:"horizontal",navheaderBg:"color_1",headerBg:"color_1",sidebarBg:"color_9",sidebarStyle:"modern",sidebarPosition:"static",headerPosition:"fixed",containerLayout:"wide",primary:"color_9",direction:Qe}],Ze=Object(r.createContext)(),$e=function(e){var t=Object(r.useState)({value:"full",label:"Full"}),a=Object(s.a)(t,2),i=a[0],n=a[1],c=Object(r.useState)({value:"fixed",label:"Fixed"}),o=Object(s.a)(c,2),l=o[0],b=o[1],d=Object(r.useState)({value:"fixed",label:"Fixed"}),u=Object(s.a)(d,2),j=u[0],f=u[1],O=Object(r.useState)({value:"vertical",label:"Vertical"}),h=Object(s.a)(O,2),p=h[0],g=h[1],x=Object(r.useState)({value:"ltr",label:"LTR"}),v=Object(s.a)(x,2),m=v[0],y=v[1],w=Object(r.useState)("color_1"),S=Object(s.a)(w,2),B=S[0],P=S[1],_=Object(r.useState)("color_1"),A=Object(s.a)(_,2),C=A[0],k=A[1],L=Object(r.useState)("color_1"),R=Object(s.a)(L,2),N=R[0],z=R[1],F=Object(r.useState)("color_1"),T=Object(s.a)(F,2),D=T[0],I=T[1],M=Object(r.useState)(!1),E=Object(s.a)(M,2),W=E[0],U=E[1],G=Object(r.useState)(!1),V=Object(s.a)(G,2),q=V[0],X=V[1],J=Object(r.useState)({value:"light",label:"Light"}),K=Object(s.a)(J,2),Q=K[0],Y=K[1],Z=Object(r.useState)({value:"wide-boxed",label:"Wide Boxed"}),$=Object(s.a)(Z,2),ee=$[0],te=$[1],ae=document.querySelector("body"),re=Object(r.useState)(0),ie=Object(s.a)(re,2),ne=ie[0],ce=ie[1],oe=Object(r.useState)(0),le=Object(s.a)(oe,2),se=le[0],be=le[1],de=function(e){P(e),ae.setAttribute("data-primary",e)},ue=function(e){k(e),ae.setAttribute("data-nav-headerbg",e)},je=function(e){z(e),ae.setAttribute("data-headerbg",e)},fe=function(e){I(e),ae.setAttribute("data-sibebarbg",e)},Oe=function(e){b(e),ae.setAttribute("data-sidebar-position",e.value)},he=function(e){y(e),ae.setAttribute("direction",e.value);var t=document.querySelector("html");t.setAttribute("dir",e.value),t.className=e.value},pe=function(e){"horizontal"===e.value&&"overlay"===i.value?(g(e),ae.setAttribute("data-layout",e.value),n({value:"full",label:"Full"}),ae.setAttribute("data-sidebar-style","full")):(g(e),ae.setAttribute("data-layout",e.value))},ge=function(e){"horizontal"===p.value&&"overlay"===e.value?alert("Sorry! Overlay is not possible in Horizontal layout."):(n(e),U("icon-hover"===e.value?"_i-hover":""),ae.setAttribute("data-sidebar-style",e.value))},xe=function(e){f(e),ae.setAttribute("data-header-position",e.value)},ve=function(e){ae.setAttribute("data-theme-version",e.value),Y(e)},me=function(e){te(e),ae.setAttribute("data-container",e.value),"boxed"===e.value&&ge({value:"overlay",label:"Overlay"})};return Object(r.useEffect)((function(){var e=document.querySelector("body");e.setAttribute("data-typography","poppins"),e.setAttribute("data-theme-version","light"),e.setAttribute("data-layout","vertical"),e.setAttribute("data-primary","color_1"),e.setAttribute("data-nav-headerbg","color_1"),e.setAttribute("data-headerbg","color_1"),e.setAttribute("data-sidebar-style","overlay"),e.setAttribute("data-sibebarbg","color_1"),e.setAttribute("data-primary","color_1"),e.setAttribute("data-sidebar-position","fixed"),e.setAttribute("data-header-position","fixed"),e.setAttribute("data-container","wide"),e.setAttribute("direction","ltr");var t=function(){ce(window.innerWidth),be(window.innerHeight),window.innerWidth>=768&&window.innerWidth<1024?e.setAttribute("data-sidebar-style","mini"):window.innerWidth<=768?e.setAttribute("data-sidebar-style","overlay"):e.setAttribute("data-sidebar-style","full")};return t(),window.addEventListener("resize",t),function(){return window.removeEventListener("resize",t)}}),[]),Object(H.jsx)(Ze.Provider,{value:{body:ae,sideBarOption:[{value:"compact",label:"Compact"},{value:"full",label:"Full"},{value:"mini",label:"Mini"},{value:"modern",label:"Modern"},{value:"overlay",label:"Overlay"},{value:"icon-hover",label:"Icon-hover"}],layoutOption:[{value:"vertical",label:"Vertical"},{value:"horizontal",label:"Horizontal"}],backgroundOption:[{value:"light",label:"Light"},{value:"dark",label:"Dark"}],sidebarposition:l,headerPositions:[{value:"fixed",label:"Fixed"},{value:"static",label:"Static"}],containerPosition:[{value:"wide-boxed",label:"Wide Boxed"},{value:"boxed",label:"Boxed"},{value:"wide",label:"Wide"}],directionPosition:[{value:"ltr",label:"LTR"},{value:"rtl",label:"RTL"}],fontFamily:[{value:"poppins",label:"Poppins"},{value:"roboto",label:"Roboto"},{value:"cairo",label:"Cairo"},{value:"opensans",label:"Open Sans"},{value:"HelveticaNeue",label:"HelveticaNeue"}],primaryColor:B,navigationHader:C,windowWidth:ne,windowHeight:se,changePrimaryColor:de,changeNavigationHader:ue,changeSideBarStyle:ge,sideBarStyle:i,changeSideBarPostion:Oe,sidebarpositions:[{value:"fixed",label:"Fixed"},{value:"static",label:"Static"}],changeHeaderPostion:xe,headerposition:j,changeSideBarLayout:pe,sidebarLayout:p,changeDirectionLayout:he,changeContainerPosition:me,direction:m,colors:["color_1","color_2","color_3","color_4","color_5","color_6","color_7","color_8","color_9","color_10","color_11","color_12","color_13","color_14","color_15"],haderColor:N,chnageHaderColor:je,chnageSidebarColor:fe,sidebarColor:D,iconHover:W,menuToggle:q,openMenuToggle:function(){"overly"===i.value?X(!0):X(!1)},changeBackground:ve,background:Q,containerPosition_:ee,setDemoTheme:function(e,t){var a={},r=Ye[e];ae.setAttribute("data-typography",r.typography),a.value=r.version,ve(a),a.value=r.layout,pe(a),de(r.primary),ue(r.navheaderBg),je(r.headerBg),a.value=r.sidebarStyle,ge(a),fe(r.sidebarBg),a.value=r.sidebarPosition,Oe(a),a.value=r.headerPosition,xe(a),a.value=r.containerLayout,me(a),a.value=t,he(a)}},children:e.children})};c.a.render(Object(H.jsx)(i.a.StrictMode,{children:Object(H.jsx)(Ke.a,{children:Object(H.jsx)(Xe.a,{basename:"/",children:Object(H.jsx)($e,{children:Object(H.jsx)(qe,{})})})})}),document.getElementById("root")),Je()}},[[636,1,2]]]);
//# sourceMappingURL=main.8fc31bb2.chunk.js.map