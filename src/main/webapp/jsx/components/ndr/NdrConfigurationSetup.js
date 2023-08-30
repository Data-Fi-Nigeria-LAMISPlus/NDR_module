import React, { useState, useEffect } from "react";
import {
  Modal,
  ModalHeader,
  ModalBody,
  Form,
  FormFeedback,
  Row,
  Col,
  Card,
  CardBody,
  FormGroup,
  Input,
  Label,
} from "reactstrap";
import Button from "@material-ui/core/Button";
import { makeStyles } from "@material-ui/core/styles";

import { Spinner } from "reactstrap";
import axios from "axios";
import { toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import { token as token, url as baseUrl } from "./../../../api";

const useStyles = makeStyles((theme) => ({
  card: {
    margin: theme.spacing(20),
    display: "flex",
    flexDirection: "column",
    alignItems: "center",
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
    width: 350,
  },
  button: {
    margin: theme.spacing(1),
  },

  root: {
    "& > *": {
      margin: theme.spacing(1),
    },
  },
  input: {
    display: "none",
  },
  error: {
    color: "#f85032",
    fontSize: "11px",
  },
  success: {
    color: "#4BB543 ",
    fontSize: "11px",
  },
}));

const DatabaseSyn = (props) => {
  const classes = useStyles();
  const defaultValues = { email: "", password: "", baseUrl: "" };
  //console.log(props)
  const [patDetails, setPatDetails] = useState(defaultValues);
  const [saving, setSaving] = useState(false);
  const [errors, setErrors] = useState({});

  const handleInputChange = (e) => {
    setPatDetails({ ...patDetails, [e.target.name]: e.target.value });
  };
  /*****  Validation */
  const validate = () => {
    let temp = { ...errors };
    temp.baseUrl = patDetails.baseUrl ? "" : "Base Url is required";
    temp.email = patDetails.email ? "" : "email is required";
    temp.password = patDetails.password ? "" : "Password is required";

    setErrors({
      ...temp,
    });
    return Object.values(temp).every((x) => x === "");
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    if (validate()) {
      setSaving(true);
      axios
        .post(`${baseUrl}ndr-emr/auto-push-configuration`, patDetails, {
          headers: { Authorization: `Bearer ${token}` },
        })
        .then((response) => {
          setSaving(false);
          props.NdrSetup();
          toast.success("NDR Setup Successfully saved");
          props.toggleModal();
        })
        .catch((error) => {
          setSaving(false);
          if (error.response && error.response.data) {
            let errorMessage =
              error.response.data.apierror &&
              error.response.data.apierror.message !== ""
                ? error.response.data.apierror.message
                : "Something went wrong, please try again";

            toast.error(errorMessage, {
              position: toast.POSITION.BOTTOM_CENTER,
            });
          } else {
            toast.error("Something went wrong. Please try again...", {
              position: toast.POSITION.BOTTOM_CENTER,
            });
          }
        });
    }
  };

  return (
    <div>
      <Modal
        isOpen={props.showModal}
        toggle={props.toggleModal}
        className={props.className}
        size="lg"
        backdrop={false}
        //backdrop="static"
      >
        <Form>
          <ModalHeader toggle={props.toggleModal}>NDR Setup</ModalHeader>
          <ModalBody>
            <Card>
              <CardBody>
                <Row>
                  <Col md={12}>
                    <FormGroup>
                      <Label>Base URL </Label>
                      <Input
                        type="text"
                        name="baseUrl"
                        id="baseUrl"
                        value={patDetails.baseUrl}
                        onChange={handleInputChange}
                        required
                      />
                      {errors.baseUrl !== "" ? (
                        <span className={classes.error}>{errors.baseUrl}</span>
                      ) : (
                        ""
                      )}
                    </FormGroup>
                  </Col>
                  <Col md={12}>
                    <FormGroup>
                      <Label>Email </Label>
                      <Input
                        type="text"
                        name="email"
                        id="email"
                        value={patDetails.email}
                        onChange={handleInputChange}
                        required
                      />
                      {errors.email !== "" ? (
                        <span className={classes.error}>{errors.email}</span>
                      ) : (
                        ""
                      )}
                    </FormGroup>
                  </Col>
                  <Col md={12}>
                    <FormGroup>
                      <Label>Password </Label>
                      <Input
                        type="password"
                        name="password"
                        id="password"
                        value={patDetails.password}
                        onChange={handleInputChange}
                        required
                      />
                      {errors.password !== "" ? (
                        <span className={classes.error}>{errors.password}</span>
                      ) : (
                        ""
                      )}
                    </FormGroup>
                  </Col>
                </Row>
                {saving ? <Spinner /> : ""}
                <br />
                <Button
                  type="submit"
                  variant="contained"
                  color="primary"
                  //startIcon={<SettingsBackupRestoreIcon />}
                  onClick={handleSubmit}
                >
                  <span style={{ textTransform: "capitalize " }}>
                    Connect & Save
                  </span>
                </Button>
              </CardBody>
            </Card>
          </ModalBody>
        </Form>
      </Modal>
    </div>
  );
};

export default DatabaseSyn;
