/*
 * This file is part of Spout.
 *
 * Copyright (c) 2011 Spout LLC <http://www.spout.org/>
 * Spout is licensed under the Spout License Version 1.
 *
 * Spout is free software: you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option)
 * any later version.
 *
 * In addition, 180 days after any changes are published, you can use the
 * software, incorporating those changes, under the terms of the MIT license,
 * as described in the Spout License Version 1.
 *
 * Spout is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE.  See the GNU Lesser General Public License for
 * more details.
 *
 * You should have received a copy of the GNU Lesser General Public License,
 * the MIT license and the Spout License Version 1 along with this program.
 * If not, see <http://www.gnu.org/licenses/> for the GNU Lesser General Public
 * License and see <http://spout.in/licensev1> for the full license, including
 * the MIT license.
 */
package org.spout.math.vector;

import java.io.Serializable;
import java.util.Random;

import org.spout.math.GenericMath;
import org.spout.math.TrigMath;

public class Vector3#E# implements Vector#E#, Comparable<Vector3#E#>, Serializable, Cloneable {
	private static final long serialVersionUID = 1;
	public static final Vector3#E# ZERO = new Vector3#E#(0, 0, 0);
	public static final Vector3#E# UNIT_X = new Vector3#E#(1, 0, 0);
	public static final Vector3#E# UNIT_Y = new Vector3#E#(0, 1, 0);
	public static final Vector3#E# UNIT_Z = new Vector3#E#(0, 0, 1);
	public static final Vector3#E# RIGHT = UNIT_X;
	public static final Vector3#E# UP = UNIT_Y;
	public static final Vector3#E# FORWARD = UNIT_Z;
	private final #e# x;
	private final #e# y;
	private final #e# z;
	private transient volatile boolean hashed = false;
	private transient volatile int hashCode = 0;

	public Vector3#E#() {
		this(0, 0, 0);
	}

	public Vector3#E#(Vector2#E# v) {
		this(v, 0);
	}

	// TODO: #e# overload

	public Vector3#E#(Vector2#E# v, #e# z) {
		this(v.getX(), v.getY(), z);
	}

	public Vector3#E#(Vector3#E# v) {
		this(v.x, v.y, v.z);
	}

	public Vector3#E#(Vector4#E# v) {
		this(v.getX(), v.getY(), v.getZ());
	}

	public Vector3#E#(VectorN#E# v) {
		this(v.get(0), v.get(1), v.size() > 2 ? v.get(2) : 0);
	}

	public Vector3#E#(double x, double y, double z) {
		this((#e#) x, (#e#) y, (#e#) z);
	}

	public Vector3#E#(#e# x, #e# y, #e# z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public #e# getX() {
		return x;
	}

	public #e# getY() {
		return y;
	}

	public #e# getZ() {
		return z;
	}

	public int getFloorX() {
		return GenericMath.floor(x);
	}

	public int getFloorY() {
		return GenericMath.floor(y);
	}

	public int getFloorZ() {
		return GenericMath.floor(z);
	}

	public Vector3#E# add(Vector3#E# v) {
		return add(v.x, v.y, v.z);
	}

	public Vector3#E# add(double x, double y, double z) {
		return add((#e#) x, (#e#) y, (#e#) z);
	}

	public Vector3#E# add(#e# x, #e# y, #e# z) {
		return new Vector3#E#(this.x + x, this.y + y, this.z + z);
	}

	public Vector3#E# sub(Vector3#E# v) {
		return sub(v.x, v.y, v.z);
	}

	public Vector3#E# sub(double x, double y, double z) {
		return sub((#e#) x, (#e#) y, (#e#) z);
	}

	public Vector3#E# sub(#e# x, #e# y, #e# z) {
		return new Vector3#E#(this.x - x, this.y - y, this.z - z);
	}

	public Vector3#E# mul(double a) {
		return mul((#e#) a);
	}

	@Override
	public Vector3#E# mul(#e# a) {
		return mul(a, a, a);
	}

	public Vector3#E# mul(Vector3#E# v) {
		return mul(v.x, v.y, v.z);
	}

	public Vector3#E# mul(double x, double y, double z) {
		return mul((#e#) x, (#e#) y, (#e#) z);
	}

	public Vector3#E# mul(#e# x, #e# y, #e# z) {
		return new Vector3#E#(this.x * x, this.y * y, this.z * z);
	}

	public Vector3#E# div(double a) {
		return div((#e#) a);
	}

	@Override
	public Vector3#E# div(#e# a) {
		return div(a, a, a);
	}

	public Vector3#E# div(Vector3#E# v) {
		return div(v.x, v.y, v.z);
	}

	public Vector3#E# div(double x, double y, double z) {
		return div((#e#) x, (#e#) y, (#e#) z);
	}

	public Vector3#E# div(#e# x, #e# y, #e# z) {
		return new Vector3#E#(this.x / x, this.y / y, this.z / z);
	}

	public #e# dot(Vector3#E# v) {
		return dot(v.x, v.y, v.z);
	}

	public #e# dot(double x, double y, double z) {
		return dot((#e#) x, (#e#) y, (#e#) z);
	}

	public #e# dot(#e# x, #e# y, #e# z) {
		return this.x * x + this.y * y + this.z * z;
	}

	public Vector3#E# cross(Vector3#E# v) {
		return cross(v.x, v.y, v.z);
	}

	public Vector3#E# cross(double x, double y, double z) {
		return cross((#e#) x, (#e#) y, (#e#) z);
	}

	public Vector3#E# cross(#e# x, #e# y, #e# z) {
		return new Vector3#E#(this.y * z - this.z * y, this.z * x - this.x * z, this.x * y - this.y * x);
	}

	public Vector3#E# pow(double pow) {
		return pow((#e#) pow);
	}

	@Override
	public Vector3#E# pow(#e# power) {
		return new Vector3#E#(Math.pow(x, power), Math.pow(y, power), Math.pow(z, power));
	}

	@Override
	public Vector3#E# ceil() {
		return new Vector3#E#(Math.ceil(x), Math.ceil(y), Math.ceil(z));
	}

	@Override
	public Vector3#E# floor() {
		return new Vector3#E#(GenericMath.floor(x), GenericMath.floor(y), GenericMath.floor(z));
	}

	@Override
	public Vector3#E# round() {
		return new Vector3#E#(Math.round(x), Math.round(y), Math.round(z));
	}

	@Override
	public Vector3#E# abs() {
		return new Vector3#E#(Math.abs(x), Math.abs(y), Math.abs(z));
	}

	@Override
	public Vector3#E# negate() {
		return new Vector3#E#(-x, -y, -z);
	}

	public Vector3#E# min(Vector3#E# v) {
		return min(v.x, v.y, v.z);
	}

	public Vector3#E# min(double x, double y, double z) {
		return min((#e#) x, (#e#) y, (#e#) z);
	}

	public Vector3#E# min(#e# x, #e# y, #e# z) {
		return new Vector3#E#(Math.min(this.x, x), Math.min(this.y, y), Math.min(this.z, z));
	}

	public Vector3#E# max(Vector3#E# v) {
		return max(v.x, v.y, v.z);
	}

	public Vector3#E# max(double x, double y, double z) {
		return max((#e#) x, (#e#) y, (#e#) z);
	}

	public Vector3#E# max(#e# x, #e# y, #e# z) {
		return new Vector3#E#(Math.max(this.x, x), Math.max(this.y, y), Math.max(this.z, z));
	}

	public #e# distanceSquared(Vector3#E# v) {
		return distanceSquared(v.x, v.y, v.z);
	}

	public #e# distanceSquared(double x, double y, double z) {
		return distanceSquared((#e#) x, (#e#) y, (#e#) z);
	}

	public #e# distanceSquared(#e# x, #e# y, #e# z) {
		return (#e#) GenericMath.lengthSquared(this.x - x, this.y - y, this.z - z);
	}

	public #e# distance(Vector3#E# v) {
		return distance(v.x, v.y, v.z);
	}

	public #e# distance(double x, double y, double z) {
		return distance((#e#) x, (#e#) y, (#e#) z);
	}

	public #e# distance(#e# x, #e# y, #e# z) {
		return (#e#) GenericMath.length(this.x - x, this.y - y, this.z - z);
	}

	@Override
	public #e# lengthSquared() {
		return (#e#) GenericMath.lengthSquared(x, y, z);
	}

	@Override
	public #e# length() {
		return (#e#) GenericMath.length(x, y, z);
	}

	@Override
	public Vector3#E# normalize() {
		final #e# length = length();
		return new Vector3#E#(x / length, y / length, z / length);
	}

	/**
	 * Returns the axis with the minimal value.
	 *
	 * @return {@link int} axis with minimal value
	 */
	@Override
	public int getMinAxis() {
		return x < y ? (x < z ? 0 : 2) : (y < z ? 1 : 2);
	}

	/**
	 * Returns the axis with the maximum value.
	 *
	 * @return {@link int} axis with maximum value
	 */
	@Override
	public int getMaxAxis() {
		return x < y ? (y < z ? 2 : 1) : (x < z ? 2 : 0);
	}

	public Vector2#E# toVector2() {
		return new Vector2#E#(this);
	}

	public Vector4#E# toVector4() {
		return toVector4(0);
	}

	public Vector4#E# toVector4(double w) {
		return toVector4((#e#) w);
	}

	public Vector4#E# toVector4(#e# w) {
		return new Vector4#E#(this, w);
	}

	public VectorN#E# toVectorN() {
		return new VectorN#E#(this);
	}

	@Override
	public #e#[] toArray() {
		return new #e#[]{x, y, z};
	}

	@Override
	public int compareTo(Vector3#E# v) {
		return (int) (lengthSquared() - v.lengthSquared());
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof Vector3#E#)) {
			return false;
		}
		final Vector3#E# vector3 = (Vector3#E#) o;
		if (#ET#.compare(vector3.x, x) != 0) {
			return false;
		}
		if (#ET#.compare(vector3.y, y) != 0) {
			return false;
		}
		if (#ET#.compare(vector3.z, z) != 0) {
			return false;
		}
		return true;
	}

	@Override
	public int hashCode() {
		if (!hashed) {
			int result = (x != +0.0f ? #ET#.#e#ToIntBits(x) : 0);
			result = 31 * result + (y != +0.0f ? #ET#.#e#ToIntBits(y) : 0);
			hashCode = 31 * result + (z != +0.0f ? #ET#.#e#ToIntBits(z) : 0);
			hashed = true;
		}
		return hashCode;
	}

	@Override
	public Vector3#E# clone() {
		return new Vector3#E#(this);
	}

	@Override
	public String toString() {
		return "(" + x + ", " + y + ", " + z + ")";
	}

	/**
	 * Gets the direction vector of a random pitch and yaw using the random specified.
	 *
	 * @param random to use
	 * @return the random direction vector
	 */
	public static Vector3#E# createRandomDirection(Random random) {
		return createDirection(random.nextFloat() * (#e#) TrigMath.TWO_PI,
				random.nextFloat() * (#e#) TrigMath.TWO_PI);
	}

	// TODO: add overloads for doubles and degree angles

	/**
	 * Gets the direction vector of a certain yaw and pitch.
	 *
	 * @param azimuth in radians
	 * @param inclination in radians
	 * @return the random direction vector
	 */
	public static Vector3#E# createDirection(#e# azimuth, #e# inclination) {
		final #e# yFact = TrigMath.cos(inclination);
		return new Vector3#E#(yFact * TrigMath.cos(azimuth), TrigMath.sin(inclination), yFact * TrigMath.sin(azimuth));
	}
}
