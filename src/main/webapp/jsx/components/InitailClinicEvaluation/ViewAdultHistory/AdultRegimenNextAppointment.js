
import React, { useEffect, useState} from "react";
import axios from "axios";
import {FormGroup, Label , CardBody, Spinner,Input,Form, InputGroup,
    InputGroupText,

} from "reactstrap";
import * as moment from 'moment';
import {makeStyles} from "@material-ui/core/styles";
import {Card, CardContent} from "@material-ui/core";
import SaveIcon from "@material-ui/icons/Save";
// import AddIcon from "@material-ui/icons/Add";
// import CancelIcon from "@material-ui/icons/Cancel";
import {ToastContainer, toast} from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import "react-widgets/dist/css/react-widgets.css";
import { useHistory, } from "react-router-dom";
// import {TiArrowBack} from 'react-icons/ti'
import {token, url as baseUrl } from "../../../../api";
import 'react-phone-input-2/lib/style.css'
import 'semantic-ui-css/semantic.min.css';
import "react-toastify/dist/ReactToastify.css";
import "react-widgets/dist/css/react-widgets.css";
import PhoneInput from 'react-phone-input-2'
import 'react-phone-input-2/lib/style.css'
import { Button} from 'semantic-ui-react'
import {  Modal } from "react-bootstrap";


const useStyles = makeStyles((theme) => ({
    card: {
        margin: theme.spacing(20),
        display: "flex",
        flexDirection: "column",
        alignItems: "center"
    },
    form: {
        width: "100%", // Fix IE 11 issue.
        marginTop: theme.spacing(3),
    },
    submit: {
        margin: theme.spacing(3, 0, 2),
    },
    cardBottom: {
        marginBottom: 20,
    },
    Select: {
        height: 45,
        width: 300,
    },
    button: {
        margin: theme.spacing(1),
    },
    root: {
        flexGrow: 1,
        "& .card-title":{
            color:'#fff',
            fontWeight:'bold'
        },
        "& .form-control":{
            borderRadius:'0.25rem',
            height:'41px'
        },
        "& .card-header:first-child": {
            borderRadius: "calc(0.25rem - 1px) calc(0.25rem - 1px) 0 0"
        },
        "& .dropdown-toggle::after": {
            display: " block !important"
        },
        "& select":{
            "-webkit-appearance": "listbox !important"
        },
        "& p":{
            color:'red'
        },
        "& label":{
            fontSize:'14px',
            color:'#014d88',
            fontWeight:'bold'
        }
    },
    demo: {
        backgroundColor: theme.palette.background.default,
    },
    inline: {
        display: "inline",
    },
    error:{
        color: '#f85032',
        fontSize: '12.8px'
    }
}));


const AdultRegimenNextAppointment = (props) => {
    const classes = useStyles()
    const [errors, setErrors] = useState({});
    let temp = { ...errors } 
    const [saving, setSaving] = useState(false); 
    const [objValues, setobjValues] = useState({nextAppointment:"", clinicianName:""});
    const [regimenObj, setRegimen] = useState({regimenLine:"", regimen:""}); 
    const [adultRegimenLine, setAdultRegimenLine] = useState([]);
    const [regimenType, setRegimenType] = useState([]);
    useEffect(() => { 
        if(props.observation.data ){
        setRegimen({...props.observation.data.regimen}) 
        objValues.nextAppointment = props.observation.data.nextAppointment
        regimenObj.regimenLine= props.observation.data.regimen.regimenLine 
        objValues.clinicianName = props.observation.data.clinicianName 
        regimenObj.regimenLine=props.observation.data.regimen.regimenLine
        RegimenType(props.observation.data.regimen.regimenLine)        
        }
        AdultRegimenLine()
    }, [props.observation.data, props.observation.data.regimen]);
    const handleRegimen =e =>{
       
        setRegimen({...regimenObj, [e.target.name]: e.target.value})
    }
    const handleInputChangeobjValues = e => { 
        setErrors({...errors, [e.target.name]: ""})            
        setobjValues ({...objValues,  [e.target.name]: e.target.value});
    }
    const handleItemClick =(page, completedMenu)=>{
        props.handleItemClick(page)
        if(props.completed.includes(completedMenu)) {

        }else{
            props.setCompleted([...props.completed, completedMenu])
        }
    } 
    const handleSelecteRegimen = e => { 
        let regimenID=  e.target.value
        //regimenTypeId regimenId
        setRegimen ({...regimenObj, regimenLine:regimenID});
        RegimenType(regimenID)           
        setErrors({...temp, [e.target.name]:""})
    }
    //GET AdultRegimenLine 
    const AdultRegimenLine =()=>{
        axios
            .get(`${baseUrl}hiv/regimen/arv/adult`,
                { headers: {"Authorization" : `Bearer ${token}`} }
            )
            .then((response) => {
                const artRegimen=response.data.filter((x)=> (x.id===1 || x.id===2 || x.id===14)) 
                setAdultRegimenLine(artRegimen);
            })
            .catch((error) => {
            //console.log(error);
            });        
    }
    //Get list of RegimenLine
    const RegimenType =(id)=>{
        axios
            .get(`${baseUrl}hiv/regimen/types/${id}`,
                { headers: {"Authorization" : `Bearer ${token}`} }
            )
            .then((response) => {
                //console.log(response.data);
                setRegimenType(response.data);
            })
            .catch((error) => {
            //console.log(error);
            });
        
    } 
    /**** Submit Button Processing  */
    const handleSubmit = (e) => { 
        e.preventDefault();  
        props.observation.data.regimen= regimenObj
        props.observation.personId =props.patientObj.id
        props.observation.data.nextAppointment=objValues.nextAppointment
        axios.put(`${baseUrl}observation/${props.observation.id}`, props.observation,
        { headers: {"Authorization" : `Bearer ${token}`}},            
        )
          .then(response => {
              setSaving(false);
              handleItemClick('', 'regimen' ) 
              props.patientObj.clinicalEvaluation=true
              toast.success("Initial Clinic Evaluation save successful",  {position: toast.POSITION.BOTTOM_CENTER});
              props.setActiveContent({...props.activeContent, route:'recent-history'})
          })
          .catch(error => {
              setSaving(false);
              if(error.response && error.response.data){
                let errorMessage = error.response.data && error.response.data.apierror.message!=="" ? error.response.data.apierror.message :  "Something went wrong, please try again";
                toast.error(errorMessage,  {position: toast.POSITION.BOTTOM_CENTER}); 
              }else{
                toast.error("Something went wrong, please try again...",  {position: toast.POSITION.BOTTOM_CENTER}); 
              }
             
          });
       
                         
    }
        
return (
        <>  
        
            <Card className={classes.root}>
                <CardBody>   
                <h2 style={{color:'#000'}}>Regimen & Next Appointment</h2>
                <br/>
                    <form >
                    {/* Medical History form inputs */}
                    <div className="row">
                    <h3>Regimen</h3>
                    <div className="form-group mb-3 col-md-6">
                    <FormGroup>
                    <Label >Original Regimen Line <span style={{ color:"red"}}> *</span></Label>
                    <Input
                            type="select"
                            name="regimenLine"
                            id="regimenLine"
                            value={regimenObj.regimenLine}
                            onChange={handleSelecteRegimen}
                            style={{border: "1px solid #014D88", borderRadius:"0.25rem"}}
                            >
                                <option value=""> Select</option>
                                    {adultRegimenLine.map((value) => (
                                    <option key={value.id} value={value.id}>
                                        {value.description}
                                    </option>
                                    ))}
                                
                        </Input>
                        {errors.regimenLine !=="" ? (
                            <span className={classes.error}>{errors.regimenLine}</span>
                            ) : "" }
                    </FormGroup>
                    </div>                    
                    <div className="form-group mb-3 col-md-6">
                    <FormGroup>
                    <Label >Original Regimen <span style={{ color:"red"}}> *</span></Label>
                    <Input
                            type="select"
                            name="regimen"
                            id="regimen"
                            value={regimenObj.regimen}
                            onChange={handleRegimen}
                            style={{border: "1px solid #014D88", borderRadius:"0.25rem"}}
                            required
                            >
                                <option value=""> Select</option>    
                                {regimenType.map((value) => (
                                    <option key={value.id} value={value.id}>
                                        {value.description}
                                    </option>
                                ))}
                        </Input>
                        {errors.regimen !=="" ? (
                            <span className={classes.error}>{errors.regimen}</span>
                            ) : "" }
                    </FormGroup>
                    </div>
                    <br/>
                    </div>
                    <div className="row">
                    <div className="form-group mb-3 col-md-6">
                            <FormGroup>
                            <Label >Next appointment <span style={{ color:"red"}}> *</span></Label>
                            <InputGroup> 
                                <Input 
                                    type="date"
                                    name="nextAppointment"
                                    id="nextAppointment"
                                    min={props.observation.dateOfObservation}
                                    onChange={handleInputChangeobjValues} 
                                    value={objValues.nextAppointment}  
                                />
                            </InputGroup>                                        
                            </FormGroup>
                    </div>
                    <div className="form-group mb-3 col-md-6">
                            <FormGroup>
                            <Label >ART Clinician Name</Label>
                            <InputGroup> 
                                <Input 
                                    type="text"
                                    name="clinicianName"
                                    id="clinicianName"
                                    onChange={handleInputChangeobjValues} 
                                    value={objValues.clinicianName}  
                                />
                            </InputGroup>
                                                                  
                            </FormGroup>
                    </div>
                    </div> 
                    <br/>
                    <Button content='Back' icon='left arrow' labelPosition='left' style={{backgroundColor:"#992E62", color:'#fff'}} onClick={()=>handleItemClick('plan', 'plan')}/>
                    <Button content='Save Record' type="submit" disabled={!saving?false:true} icon='right arrow' labelPosition='right' style={{backgroundColor:"#014d88", color:'#fff'}} onClick={handleSubmit}/>
                    
                    </form>
                    
                </CardBody>
            </Card> 
                                     
        </>
    );
};

export default AdultRegimenNextAppointment