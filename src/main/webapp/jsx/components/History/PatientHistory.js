import React, { useEffect, useState } from 'react'
import MaterialTable from 'material-table';
import axios from "axios";
import { url as baseUrl } from "./../../../api";
import { token as token } from "./../../../api";
import { forwardRef } from 'react';
import 'semantic-ui-css/semantic.min.css';



import AddBox from '@material-ui/icons/AddBox';
import ArrowUpward from '@material-ui/icons/ArrowUpward';
import Check from '@material-ui/icons/Check';
import ChevronLeft from '@material-ui/icons/ChevronLeft';
import ChevronRight from '@material-ui/icons/ChevronRight';
import Clear from '@material-ui/icons/Clear';
import DeleteOutline from '@material-ui/icons/DeleteOutline';
import Edit from '@material-ui/icons/Edit';
import FilterList from '@material-ui/icons/FilterList';
import FirstPage from '@material-ui/icons/FirstPage';
import LastPage from '@material-ui/icons/LastPage';
import Remove from '@material-ui/icons/Remove';
import SaveAlt from '@material-ui/icons/SaveAlt';
import Search from '@material-ui/icons/Search';
import ViewColumn from '@material-ui/icons/ViewColumn';
import 'react-toastify/dist/ReactToastify.css';
import 'react-widgets/dist/css/react-widgets.css';
import { makeStyles } from '@material-ui/core/styles'
import { useHistory } from "react-router-dom";

import {FaSyringe, FaUserEdit, FaShare, FaTrash} from "react-icons/fa";
import { MdDashboard } from "react-icons/md";
//import {Menu,MenuList,MenuButton,MenuItem,} from "@reach/menu-button";
import "@reach/menu-button/styles.css";
import { Label } from 'semantic-ui-react'
import { RowingSharp } from '@mui/icons-material';
import { Dropdown,Button, Menu, Icon } from 'semantic-ui-react'


const tableIcons = {
Add: forwardRef((props, ref) => <AddBox {...props} ref={ref} />),
Check: forwardRef((props, ref) => <Check {...props} ref={ref} />),
Clear: forwardRef((props, ref) => <Clear {...props} ref={ref} />),
Delete: forwardRef((props, ref) => <DeleteOutline {...props} ref={ref} />),
DetailPanel: forwardRef((props, ref) => <ChevronRight {...props} ref={ref} />),
Edit: forwardRef((props, ref) => <Edit {...props} ref={ref} />),
Export: forwardRef((props, ref) => <SaveAlt {...props} ref={ref} />),
Filter: forwardRef((props, ref) => <FilterList {...props} ref={ref} />),
FirstPage: forwardRef((props, ref) => <FirstPage {...props} ref={ref} />),
LastPage: forwardRef((props, ref) => <LastPage {...props} ref={ref} />),
NextPage: forwardRef((props, ref) => <ChevronRight {...props} ref={ref} />),
PreviousPage: forwardRef((props, ref) => <ChevronLeft {...props} ref={ref} />),
ResetSearch: forwardRef((props, ref) => <Clear {...props} ref={ref} />),
Search: forwardRef((props, ref) => <Search {...props} ref={ref} />),
SortArrow: forwardRef((props, ref) => <ArrowUpward {...props} ref={ref} />),
ThirdStateCheck: forwardRef((props, ref) => <Remove {...props} ref={ref} />),
ViewColumn: forwardRef((props, ref) => <ViewColumn {...props} ref={ref} />)
};

const useStyles = makeStyles(theme => ({
    card: {
        margin: theme.spacing(20),
        display: 'flex',
        flexDirection: 'column',
        alignItems: 'center'
    },
    form: {
        width: '100%', // Fix IE 11 issue.
        marginTop: theme.spacing(3)
    },
    submit: {
        margin: theme.spacing(3, 0, 2)
    },
    cardBottom: {
        marginBottom: 20
    },
    Select: {
        height: 45,
        width: 350
    },
    button: {
        margin: theme.spacing(1)
    },

    root: {
        '& > *': {
            margin: theme.spacing(1)
        }
    },
    input: {
        display: 'none'
    },
    error: {
        color: "#f85032",
        fontSize: "11px",
    },
    success: {
        color: "#4BB543 ",
        fontSize: "11px",
    }, 
}))



const PatientnHistory = (props) => {
    const [recentActivities, setRecentActivities] = useState([])
    const [loading, setLoading] = useState(true)
    let history = useHistory();
    let patientHistoryObject = []
    useEffect(() => {
        PatientHistory()
      }, [props.patientObj.id]);
        ///GET LIST OF Patients
        const PatientHistory =()=>{
            setLoading(true)
            axios
               .get(`${baseUrl}hiv/patients/${props.patientObj.id}/activities?full=false`,
                   { headers: {"Authorization" : `Bearer ${token}`} }
               )
               .then((response) => {
                setLoading(false)
                        let HistoryObject= []
                        response.data.forEach(function(value, index, array) {
                            const dataObj = value.activities 
                            console.log(dataObj)                 
                            if(dataObj[index]) {
                                dataObj.forEach(function(value, index, array) {
                                    HistoryObject.push(value)
                                })                       
                            }                   
                        });
                    setRecentActivities(HistoryObject)
                })

               .catch((error) => {
               //console.log(error);
               });
           
          }
    const LoadViewPage =(row)=>{
        
        if(row.path==='Mental-health'){        
            props.setActiveContent({...props.activeContent, route:'mental-health-view', id:row.id})

        }else if(row.path==='Art-commence'){
            props.setActiveContent({...props.activeContent, route:'art-commencement-view', id:row.id})

        }else if(row.path==='Clinical-evaluation'){
            props.setActiveContent({...props.activeContent, route:'adult-clinic-eveluation-view', id:row.id})

        }else if(row.path==='EAC-First'){
            props.setActiveContent({...props.activeContent, route:'first-eac-history', id:row.id})
        }
        else if(row.path==='EAC-Second'){
            props.setActiveContent({...props.activeContent, route:'second-eac-history', id:row.id})
        }
        else if(row.path==='EAC-Completed'){
            props.setActiveContent({...props.activeContent, route:'completed-eac-history', id:row.id})
        }else if(row.path==='hiv-enrollment'){
            history.push({
                pathname: '/register-patient',
                state: { id: row.id }
            });
            //props.setActiveContent({...props.activeContent, route:'mental-health-history', id:row.id})
        }else if(row.path==='Pharmacy'){
            props.setActiveContent({...props.activeContent, route:'mental-health-history', id:row.id})

        }else if(row.path==='Laboratory'){
            props.setActiveContent({...props.activeContent, route:'mental-health-history', id:row.id})

        }else{

        }
        
    }



  return (
    <div>
        
    <br/>
       
            <MaterialTable
            icons={tableIcons}
              title="Patient History "
              columns={[
                { title: "Name", field: "name" },
                {
                  title: "Encounter Date",
                  field: "date",
                },               
                // { title: "Status", field: "status", filtering: false },        
                { title: "Actions", field: "actions", filtering: false }, 
              ]}
              isLoading={loading}
              data={recentActivities && recentActivities.map((row) => ({
                   name: row.name,
                   date: row.date,
                   actions:
            
                    <div>
                        <Menu.Menu position='right'  >
                        <Menu.Item >
                            <Button style={{backgroundColor:'rgb(153,46,98)'}} primary>
                            <Dropdown item text='Action'>

                            <Dropdown.Menu >
                                {row.viewable && ( <Dropdown.Item onClick={()=>LoadViewPage(row)}> <Icon name='eye' />View</Dropdown.Item>)}
                                {/* {row.viewable && ( <Dropdown.Item  ><Icon name='edit' />Edit</Dropdown.Item>)}
                                {row.viewable && ( <Dropdown.Item  > <Icon name='trash' /> Delete</Dropdown.Item>)} */}
                            </Dropdown.Menu>
                        </Dropdown>
                            </Button>
                        </Menu.Item>
                        </Menu.Menu>
                  </div>
                  
                  }))}
            
                        options={{
                          headerStyle: {
                              backgroundColor: "#014d88",
                              color: "#fff",
                          },
                          searchFieldStyle: {
                              width : '200%',
                              margingLeft: '250px',
                          },
                          filtering: false,
                          exportButton: false,
                          searchFieldAlignment: 'left',
                          pageSizeOptions:[10,20,100],
                          pageSize:10,
                          debounceInterval: 400
                      }}
            />
         
    </div>
  );
}

export default PatientnHistory;


