import React, { useState, useEffect } from "react";
import { Grid, Segment, Label, Icon, List, Button, Card, } from 'semantic-ui-react'
// Page titie
import { FormGroup, Input, Label as FormLabelName, InputGroup, InputGroupText } from "reactstrap";
import ADR from './ADR/Index'
import OpportunisticInfection from './OpportunisticInfection/Index'
import TBScreening from './TBScreening/Index'
import { url as baseUrl, token } from "../../../api";
import MatButton from '@material-ui/core/Button'
import { makeStyles } from '@material-ui/core/styles'
import SaveIcon from '@material-ui/icons/Save'
import axios from "axios";
//import AddVitals from './Vitals/AddVitals'
import AddAllergy from './Allergies/AddAllergy'
import AddCondition from './Conditions/AddCondition'
import PostPatient from './PostPatient/Index'
import moment from "moment";
import { toast } from "react-toastify";


let adherenceLevelObj = []
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

const ClinicVisit = (props) => {
  const patientObj = props.patientObj ? props.patientObj : {}
  console.log(patientObj)
  const classes = useStyles()
  const [saving, setSaving] = useState(false);
  const [clinicalStage, setClinicalStage] = useState([]);
  const [functionalStatus, setFunctionalStatus] = useState([]);
  const [adherenceLevel, setAdherenceLevel] = useState([]);
  const [tbStatus, setTbStatus] = useState([]);
  const [TBForms, setTBForms] = useState(false)
  // const [addVitalModal, setAddVitalModal] = useState(false);
  // const AddVitalToggle = () => setAddVitalModal(!addVitalModal)
  const [addConditionModal, setAddConditionModal] = useState(false);
  const AddConditionToggle = () => setAddConditionModal(!addConditionModal)
  const [addAllergyModal, setAddAllergyModal] = useState(false);
  const AddAllergyToggle = () => setAddAllergyModal(!addAllergyModal)
  const [postPatientModal, setPostPatientModal] = useState(false);
  const PostPatientToggle = () => setPostPatientModal(!postPatientModal)
  const [currentVitalSigns, setcurrentVitalSigns] = useState({})
  const [showCurrentVitalSigns, setShowCurrentVitalSigns] = useState(false)
  //opportunistic infection Object
  const [infection, setInfection] = useState({ illnessInfection: "", ondateInfection: "" });
  const [infectionList, setInfectionList] = useState([]);
  //ADR array Object 
  const [adrObj, setAdrObj] = useState({ adr: "", adrOnsetDate: "" });
  const [adrList, setAdrList] = useState([]);
  const [objValues, setObjValues] = useState({
    adherenceLevel: "",
    adheres: {},
    adrScreened: "",
    adverseDrugReactions: {},
    artStatusId: patientObj.artCommence.id,
    cd4: "",
    cd4Percentage: 0,
    clinicalNote: "",
    clinicalStageId: 0,
    facilityId: 0,
    functionalStatusId: 0,
    hivEnrollmentId: patientObj.enrollment.id,
    nextAppointment: "",
    lmpDate: "",
    oiScreened: "",
    opportunisticInfections: {},
    personId: patientObj.id,
    tbScreen: {},
    stiIds: "",
    stiTreated: "",
    uuid: "",
    visitDate: "",
    whoStagingId: 0
  });
  const [vital, setVitalSignDto] = useState({
    bodyWeight: "",
    diastolic: "",
    encounterDate: "",
    facilityId: 1,
    height: "",
    personId: props.patientObj.id,
    serviceTypeId: 1,
    systolic: ""
  })
  const [tbObj, setTbObj] = useState({
    currentOnIpt: "",
    couching: "",
    antiTBDrug: "",
    nightSweat: "",
    fever: "",
    contactWithTBCase: "",
    lethergy: "",
    tbStatusId: ""
  });

  useEffect(() => {
    FunctionalStatus();
    WhoStaging();
    AdherenceLevel();
    TBStatus();
    VitalSigns()
  }, []);
  //Check for the last Vital Signs
  const VitalSigns = () => {
    axios
      .get(`${baseUrl}patient/vital-sign/person/${props.patientObj.id}`,
        { headers: { "Authorization": `Bearer ${token}` } }
      )
      .then((response) => {

        const lastVitalSigns = response.data[response.data.length - 1]
        if (lastVitalSigns.encounterDate === moment(new Date()).format("YYYY-MM-DD") === true) {
          setcurrentVitalSigns(lastVitalSigns)
          setShowCurrentVitalSigns(true)
        }
      })
      .catch((error) => {
        //console.log(error);
      });
  }
  //Get list of WhoStaging
  const WhoStaging = () => {
    axios
      .get(`${baseUrl}application-codesets/v2/CLINICAL_STAGE`,
        { headers: { "Authorization": `Bearer ${token}` } }
      )
      .then((response) => {
        //console.log(response.data);
        setClinicalStage(response.data);
      })
      .catch((error) => {
        //console.log(error);
      });

  }
  ///GET LIST OF FUNCTIONAL%20_STATUS
  // TB STATUS
  const TBStatus = () => {
    axios
      .get(`${baseUrl}application-codesets/v2/TB_STATUS`,
        { headers: { "Authorization": `Bearer ${token}` } }
      )
      .then((response) => {
        //console.log(response.data);
        setTbStatus(response.data);
      })
      .catch((error) => {
        //console.log(error);
      });

  }

  async function FunctionalStatus() {
    axios
      .get(`${baseUrl}application-codesets/v2/FUNCTIONAL%20_STATUS`,
        { headers: { "Authorization": `Bearer ${token}` } }
      )
      .then((response) => {

        setFunctionalStatus(response.data);
        //setValues(response.data)
      })
      .catch((error) => {
      });
  }
  ///Level of Adherence
  async function AdherenceLevel() {
    axios
      .get(`${baseUrl}application-codesets/v2/PrEP_LEVEL_OF_ADHERENCE`,
        { headers: { "Authorization": `Bearer ${token}` } }
      )
      .then((response) => {
        setAdherenceLevel(response.data);

      })
      .catch((error) => {
      });
  }
  const handleInputChange = e => {
    setObjValues({ ...objValues, [e.target.name]: e.target.value });
    if (e.target.name === "whoStagingId") {
      if (e.target.value === "NO") {
        setTBForms(true)
      } else {
        setTBForms(false)
      }
    }
  }
  const handleInputChangeVitalSignDto = e => {
    setVitalSignDto({ ...vital, [e.target.name]: e.target.value });
  }

  const addConditionsModal = () => {
    //setpatientObj({...patientObj, ...row});
    setAddConditionModal(!addConditionModal)
  }
  const addAllergiesModal = () => {
    //setpatientObj({...patientObj, ...row});
    setAddAllergyModal(!addAllergyModal)
  }
  const PostPatientService = (row) => {
    //setpatientObj({...patientObj, ...row});
    setPostPatientModal(!postPatientModal)
  }
  //Handle CheckBox 
  const handleCheckBox = e => {
    if (e.target.checked) {
      setVitalSignDto({ ...currentVitalSigns })
    } else {
      setVitalSignDto({
        bodyWeight: "",
        diastolic: "",
        encounterDate: "",
        facilityId: 1,
        height: "",
        personId: props.patientObj.id,
        serviceTypeId: 1,
        systolic: ""
      })
    }
  }
  /**** Submit Button Processing  */
  const handleSubmit = (e) => {
    e.preventDefault();
    objValues.visitDate = vital.encounterDate
    objValues.adverseDrugReactions = adrList
    objValues.opportunisticInfections = infectionList
    objValues.tbScreen = tbObj
    objValues['vitalSignDto'] = vital
    axios.post(`${baseUrl}hiv/art/clinic-visit/`, objValues,
      { headers: { "Authorization": `Bearer ${token}` } },

    )
      .then(response => {
        setSaving(false);
        toast.success("Clinic Visit save successful");
        props.setActiveContent('recent-history')
      })
      .catch(error => {
        setSaving(false);
        if (error.apierror) {
          toast.error(error.apierror.message);
        } else {
          toast.error("Something went wrong. Please try again...");
        }
      });
  }


  return (
    <div>
      <h2>Clinic Follow-up Visit</h2>
      <Grid columns='equal'>
        <Grid.Column>
          {showCurrentVitalSigns && (
            <Segment>
              <Label as='a' color='blue' ribbon>
                Recent Vitals
              </Label>
              <br />
              <List celled>
                {currentVitalSigns.pulse!==null ? <List.Item>Pulse <span className="float-end"><b>{currentVitalSigns.pulse}bpm</b></span></List.Item> :""}
                {currentVitalSigns.respiratoryRate!==null ?<List.Item>Respiratory Rate <span className="float-end"><b>{currentVitalSigns.respiratoryRate}bpm</b></span></List.Item> :""}
                 {currentVitalSigns.temperature!==null ?<List.Item>Temperature <span className="float-end"><b>{currentVitalSigns.temperature}<sup>0</sup>C</b></span></List.Item> :""}
                 {currentVitalSigns.systolic!==null && currentVitalSigns.diastolic!==null ?<List.Item>Blood Presure <span className="float-end"><b>{currentVitalSigns.systolic}/{currentVitalSigns.diastolic}</b> mmHg</span></List.Item> :""}
                 {currentVitalSigns.height!==null ?<List.Item>Height <span className="float-end"><b>{currentVitalSigns.height}cm</b></span></List.Item> :""}
                 {currentVitalSigns.bodyWeight!==null ?<List.Item>Weight <span className="float-end"><b>{currentVitalSigns.bodyWeight}kg</b></span></List.Item> :""}
              </List>
            </Segment>
          )}
          <Segment>
            <Label as='a' color='black' ribbon>
              Conditions
            </Label>
            <Label as='a' color='teal' onClick={() => addConditionsModal()} className="float-end" size='mini' >
              <Icon name='plus' />
            </Label>
            <br />
            <Label as='a' color='white' size="mini" pointing>
              Laser Fever
            </Label>
            <Label as='a' color='white' size="mini" pointing>
              Typoid Fever
            </Label>
            <Label as='a' color='white' size="mini" pointing>
              Asthma
            </Label>

          </Segment>
          <Segment>
            <Label as='a' color='red' ribbon>
              Allergies
            </Label>
            <Label as='a' color='teal' onClick={() => addAllergiesModal()} className="float-end" size='mini' >
              <Icon name='plus' />
            </Label>
            <br /><br />
            <Label.Group color='blue'>

              <Label as='a' size="mini">dust</Label>
              <Label as='a' size="mini">smoke</Label>

            </Label.Group>

          </Segment>
        </Grid.Column>
        <Grid.Column width={9}>
          <Segment>
            <Label as='a' color='blue' ribbon>
              <b>Vital Signs</b>
            </Label>
            <br /><br />
            <div className="row">
              <div className="form-group mb-3 col-md-6">
                <FormGroup>
                  <FormLabelName >Date of Visit </FormLabelName>
                  <Input
                    type="date"
                    name="encounterDate"
                    id="encounterDate"
                    value={vital.encounterDate}
                    onChange={handleInputChangeVitalSignDto}
                    max={moment(new Date()).format("YYYY-MM-DD")}
                    required
                  >
                  </Input>

                </FormGroup>
              </div>
              <div className="form-group mb-3 col-md-6">
                {showCurrentVitalSigns && (
                  <div className="form-check custom-checkbox ml-1 ">
                    <input
                      type="checkbox"
                      className="form-check-input"
                      name="currentVitalSigns"
                      id="currentVitalSigns"
                      onChange={handleCheckBox}
                    />
                    <label
                      className="form-check-label"
                      htmlFor="basic_checkbox_1"
                    >
                      use current Vital Signs
                    </label>
                  </div>
                )}
              </div>
              <div className="mb-3 col-md-6">
                <FormGroup>
                  <FormLabelName >Body Weight</FormLabelName>

                  <InputGroup>
                    <InputGroupText>
                      kg
                    </InputGroupText>
                    <Input
                      type="number"
                      name="bodyWeight"
                      id="bodyWeight"
                      onChange={handleInputChangeVitalSignDto}
                      value={vital.bodyWeight}
                    />
                  </InputGroup>
                  {vital.bodyWeight > 200 ? (
                    <span className={classes.error}>{"Body Weight cannot be greater than 200."}</span>
                  ) : ""}
                </FormGroup>
              </div>

              <div className="form-group mb-3 col-md-6">
                <FormGroup>
                  <FormLabelName >Height</FormLabelName>
                  <InputGroup>
                    <InputGroupText>
                      m
                    </InputGroupText>
                    <Input
                      type="number"
                      name="height"
                      id="height"
                      onChange={handleInputChangeVitalSignDto}
                      value={vital.height}
                    />

                  </InputGroup>
                  {vital.height > 3 ? (
                    <span className={classes.error}>{"Height cannot be greater than 3."}</span>
                  ) : ""}
                </FormGroup>
              </div>
              <div className="form-group mb-3 col-md-6">
                <FormGroup>
                  <FormLabelName >Blood Pressure</FormLabelName>
                  <InputGroup>
                    <InputGroupText>
                      systolic(mmHg)
                    </InputGroupText>
                    <Input
                      type="number"
                      name="systolic"
                      id="systolic"
                      onChange={handleInputChangeVitalSignDto}
                      value={vital.systolic}
                    />

                  </InputGroup>
                  {vital.systolic > 200 ? (
                    <span className={classes.error}>{"Blood Pressure cannot be greater than 200."}</span>
                  ) : ""}
                </FormGroup>
              </div>
              <div className="form-group mb-3 col-md-6">
                <FormGroup>
                  <FormLabelName >Blood Pressure</FormLabelName>

                  <InputGroup>
                    <InputGroupText>
                      diastolic(mmHg)
                    </InputGroupText>
                    <Input
                      type="text"
                      name="diastolic"
                      id="diastolic"
                      onChange={handleInputChangeVitalSignDto}
                      value={vital.diastolic}
                    />

                  </InputGroup>
                  {vital.diastolic > 200 ? (
                    <span className={classes.error}>{"Blood Pressure cannot be greater than 200."}</span>
                  ) : ""}
                </FormGroup>
              </div>
            </div>
            <Label as='a' color='black' ribbon>
              <b>Consultation</b>
            </Label>
            <br /><br />

            <div className=" mb-3">
              <FormLabelName >Clinical Notes</FormLabelName>
              <textarea
                name="clinicalNote"
                className="form-control"
                value={objValues.clinicalNote}
                onChange={handleInputChange}
              ></textarea>
            </div>
            <div className="row">

              <div className=" mb-3 col-md-6">
                <FormGroup>
                  <FormLabelName >WHO Staging</FormLabelName>
                  <Input
                    type="select"
                    name="whoStagingId"
                    id="whoStagingId"
                    value={objValues.whoStagingId}
                    onChange={handleInputChange}
                    required
                  >
                    <option value="select">Select </option>

                    {clinicalStage.map((value) => (
                      <option key={value.id} value={value.id}>
                        {value.display}
                      </option>
                    ))}
                  </Input>

                </FormGroup>
              </div>
              <div className=" mb-3 col-md-6">
                <FormGroup>
                  <FormLabelName >Functional Status</FormLabelName>
                  <Input
                    type="select"
                    name="functionalStatusId"
                    id="functionalStatusId"
                    value={objValues.functionalStatusId}
                    onChange={handleInputChange}
                    required
                  >
                    <option value="select">Select </option>

                    {functionalStatus.map((value) => (
                      <option key={value.id} value={value.id}>
                        {value.display}
                      </option>
                    ))}
                  </Input>
                </FormGroup>
              </div>
              <div className=" mb-3 col-md-6">
                <FormGroup>
                  <FormLabelName >Level of Adherence</FormLabelName>
                  <Input
                    type="select"
                    name="adherenceLevel"
                    id="adherenceLevel"
                    value={objValues.adherenceLevel}
                    onChange={handleInputChange}
                    required
                  >
                    <option value="select">Select </option>

                    {adherenceLevel.map((value) => (
                      <option key={value.id} value={value.id}>
                        {value.display}
                      </option>
                    ))}
                  </Input>
                </FormGroup>
              </div>

            </div>
            <br />
            <Label as='a' color='red' ribbon>
              Opportunistic Infection
            </Label>
            <br /><br />
            <OpportunisticInfection setInfection={setInfection} infection={infection} setInfectionList={setInfectionList} infectionList={infectionList} />
            <br />
            <Label as='a' color='pink' ribbon>
              ADR
            </Label>
            <br /><br />
            <ADR setAdrObj={setAdrObj} adrObj={adrObj} setAdrList={setAdrList} adrList={adrList} />
            <br />
            <Label as='a' color='teal' ribbon>
              TB Screening
            </Label>
            <br /><br />
            {/* TB Screening Form */}
            <TBScreening tbObj={tbObj} setTbObj={setTbObj} />
            <br />
            <MatButton
              type="submit"
              variant="contained"
              color="primary"
              className={classes.button}
              startIcon={<SaveIcon />}
              onClick={handleSubmit}
            >
              {!saving ? (
                <span style={{ textTransform: "capitalize" }}>Save</span>
              ) : (
                <span style={{ textTransform: "capitalize" }}>Saving...</span>
              )}
            </MatButton>
          </Segment>
        </Grid.Column>
        <Grid.Column>
          <Segment>
            <List>
              <List.Item>
                <Button icon labelPosition='right' color='teal' fluid onClick={() => PostPatientService()}>
                  <Icon name='external alternate' />
                  Post Patient
                </Button>
              </List.Item>
              {/* <List.Item>
                  <Button icon labelPosition='right' color='green' fluid>
                      <Icon name='eye' />
                        View History
                    </Button>
                  </List.Item> */}
              <List.Item>
                <Button icon labelPosition='right' color='blue' fluid>
                  <Icon name='calendar alternate' />
                  Appointment
                </Button>
              </List.Item>
            </List>
            <Card>
              <Card.Content>
                <b>Previous Clinical Notes</b>
              </Card.Content>
              <Card.Content>
                {/* <Feed>
                <Feed.Event>
                  <Feed.Content>
                    <Feed.Date content='20-03-2022' />
                    <Feed.Summary>
                      The malaria is plus 3 and and need more attention
                    </Feed.Summary>
                  </Feed.Content>
                </Feed.Event>
                <hr/>
                <Feed.Event>
                  <Feed.Content>
                    <Feed.Date content='20-05-2022' />
                    <Feed.Summary>
                      Blood presure is too high
                    </Feed.Summary>
                  </Feed.Content>
                </Feed.Event>
              </Feed> */}
              </Card.Content>
            </Card>

          </Segment>
        </Grid.Column>
      </Grid>
      {/* <AddVitals toggle={AddVitalToggle} showModal={addVitalModal} /> */}
      <AddAllergy toggle={AddAllergyToggle} showModal={addAllergyModal} />
      <AddCondition toggle={AddConditionToggle} showModal={addConditionModal} />
      <PostPatient toggle={PostPatientToggle} showModal={postPatientModal} />
    </div>
  );
};

export default ClinicVisit;
