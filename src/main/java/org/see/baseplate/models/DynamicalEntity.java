/*-
 * Copyright (c) 2026 Hridyanshu Aatreya
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 * 3. Neither the name of the copyright holder nor the names of its
 *    contributors may be used to endorse or promote products derived from
 *    this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE AUTHOR AND CONTRIBUTORS ``AS IS'' AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED.  IN NO EVENT SHALL THE AUTHOR OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS
 * OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION)
 * HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT
 * LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY
 * OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 *
 */

package org.see.baseplate.models;

import org.apache.commons.geometry.euclidean.threed.Vector3D;
import org.apache.commons.numbers.quaternion.Quaternion;
import org.ejml.simple.SimpleMatrix;
import org.see.baseplate.encoding.QuaternionCoder;
import org.see.baseplate.encoding.SpaceTimeCoordinateStateCoder;
import org.see.baseplate.types.SpaceTimeCoordinateState;
import org.see.skf.annotations.Attribute;
import org.see.skf.annotations.ObjectClass;
import org.see.skf.core.UpdatableObjectInstance;
import org.see.skf.util.encoding.HLAfloat64LECoder;
import org.see.skf.runtime.AccessLevel;
import org.see.baseplate.encoding.SimpleMatrixCoder;
import org.see.baseplate.encoding.Vector3DCoder;
import org.see.skf.util.encoding.HLAunicodeStringCoder;

@ObjectClass(name = "HLAobjectRoot.PhysicalEntity.DynamicalEntity")
public class DynamicalEntity extends UpdatableObjectInstance {
    @Attribute(name = "name", coder = HLAunicodeStringCoder.class, access = AccessLevel.NONE)
    private String name;

    @Attribute(name = "type", coder = HLAunicodeStringCoder.class, access = AccessLevel.NONE)
    private String type;

    @Attribute(name = "status", coder = HLAunicodeStringCoder.class, access = AccessLevel.NONE)
    private String status;

    @Attribute(name = "parent_reference_frame", coder = HLAunicodeStringCoder.class, access = AccessLevel.NONE)
    private String parentReferenceFrame;

    @Attribute(name = "state",  coder = SpaceTimeCoordinateStateCoder.class, access = AccessLevel.NONE)
    private SpaceTimeCoordinateState state;

    @Attribute(name = "acceleration", coder = Vector3DCoder.class, access = AccessLevel.NONE)
    private Vector3D acceleration;

    @Attribute(name = "rotational_acceleration", coder = Vector3DCoder.class, access = AccessLevel.NONE)
    private Vector3D rotationalAcceleration;

    @Attribute(name = "center_of_mass", coder = Vector3DCoder.class, access = AccessLevel.NONE)
    private Vector3D centerOfMass;

    @Attribute(name = "body_wrt_structural", coder = QuaternionCoder.class, access = AccessLevel.NONE)
    private Quaternion bodyWrtStructural;

    @Attribute(name = "force", coder = Vector3DCoder.class, access = AccessLevel.NONE)
    private Vector3D force;

    @Attribute(name = "torque", coder = Vector3DCoder.class, access = AccessLevel.NONE)
    private Vector3D torque;

    @Attribute(name = "mass", coder = HLAfloat64LECoder.class, access = AccessLevel.NONE)
    private double mass;

    @Attribute(name = "mass_rate", coder = HLAfloat64LECoder.class, access = AccessLevel.NONE)
    private double massRate;

    @Attribute(name = "inertia", coder = SimpleMatrixCoder.class, access = AccessLevel.NONE)
    private SimpleMatrix inertia;

    @Attribute(name = "inertia_rate", coder = SimpleMatrixCoder.class, access = AccessLevel.NONE)
    private SimpleMatrix inertiaRate;

    public DynamicalEntity() {
        this.name = "";
        this.type = "";
        this.status = "";
        this.parentReferenceFrame = "";
        this.state = new SpaceTimeCoordinateState();
        this.acceleration = Vector3D.of(0, 0, 0);
        this.rotationalAcceleration = Vector3D.of(0, 0, 0);
        this.centerOfMass = Vector3D.of(0, 0, 0);
        this.bodyWrtStructural = Quaternion.of(0, 0, 0, 0);
        force = Vector3D.of(0, 0, 0);
        torque = Vector3D.of(0, 0, 0);
        mass = 0.0;
        massRate = 0.0;
        inertia = new SimpleMatrix(3, 3);
        inertiaRate = new SimpleMatrix(3, 3);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getParentReferenceFrame() {
        return parentReferenceFrame;
    }

    public void setParentReferenceFrame(String parentReferenceFrame) {
        this.parentReferenceFrame = parentReferenceFrame;
    }

    public SpaceTimeCoordinateState getState() {
        return state;
    }

    public void setState(SpaceTimeCoordinateState state) {
        this.state = state;
    }

    public Vector3D getAcceleration() {
        return acceleration;
    }

    public void setAcceleration(Vector3D acceleration) {
        this.acceleration = acceleration;
    }

    public Vector3D getRotationalAcceleration() {
        return rotationalAcceleration;
    }

    public void setRotationalAcceleration(Vector3D rotationalAcceleration) {
        this.rotationalAcceleration = rotationalAcceleration;
    }

    public Vector3D getCenterOfMass() {
        return centerOfMass;
    }

    public void setCenterOfMass(Vector3D centerOfMass) {
        this.centerOfMass = centerOfMass;
    }

    public Quaternion getBodyWrtStructural() {
        return bodyWrtStructural;
    }

    public void setBodyWrtStructural(Quaternion bodyWrtStructural) {
        this.bodyWrtStructural = bodyWrtStructural;
    }

    public Vector3D getForce() {
        return force;
    }

    public void setForce(Vector3D force) {
        this.force = force;
    }

    public Vector3D getTorque() {
        return torque;
    }

    public void setTorque(Vector3D torque) {
        this.torque = torque;
    }

    public double getMass() {
        return mass;
    }

    public void setMass(double mass) {
        this.mass = mass;
    }

    public double getMassRate() {
        return massRate;
    }

    public void setMassRate(double massRate) {
        this.massRate = massRate;
    }

    public SimpleMatrix getInertia() {
        return inertia;
    }

    public void setInertia(SimpleMatrix inertia) {
        this.inertia = inertia;
    }

    public SimpleMatrix getInertiaRate() {
        return inertiaRate;
    }

    public void setInertiaRate(SimpleMatrix inertiaRate) {
        this.inertiaRate = inertiaRate;
    }
}
